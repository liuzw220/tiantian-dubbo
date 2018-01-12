package com.tiantian.manager.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiantian.item.apis.PropertieService;
import com.tiantian.item.beans.PicUploadResult;

/**
 * 图片上传
 * 
 * @author 传智播客 张志君
 * 
 * @date 2014年10月14日
 * 
 * @version V1.0
 */
@Controller
@RequestMapping("/pic")
public class PicUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PicUploadController.class);

	 @Autowired(required=false)
	private PropertieService propertieService;
	
	private static final ObjectMapper mapper = new ObjectMapper();

	// 允许上传的格式
	private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("uploadFile") MultipartFile uploadFile , HttpServletResponse response) throws Exception {
		// 校验图片格式
		boolean isLegal = false;
		for (String type : IMAGE_TYPE) {
			if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
				isLegal = true;
				break;
			}
		}

		// 封装Result对象，并且将文件的byte数组放置到result对象中
		PicUploadResult fileUploadResult = new PicUploadResult();

		// 状态
		fileUploadResult.setError(isLegal ? 0 : 1);

		// 文件新路径
		String filePath = getFilePath(uploadFile.getOriginalFilename());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Pic file upload .[{}] to [{}] .", uploadFile.getOriginalFilename(), filePath);
		}

		// 生成图片的绝对引用地址
		String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, PropertieService.REPOSITORY_PATH), "\\", "/");
		fileUploadResult.setUrl(PropertieService.IMAGE_BASE_URL + picUrl);

		File newFile = new File(filePath);

		// 写文件到磁盘
		uploadFile.transferTo(newFile);

		// 校验图片是否合法
		isLegal = false;
		try {
			BufferedImage image = ImageIO.read(newFile);
			if (image != null) {
				fileUploadResult.setWidth(image.getWidth() + "");
				fileUploadResult.setHeight(image.getHeight() + "");
				isLegal = true;
			}
		} catch (IOException e) {
		}

		// 状态
		fileUploadResult.setError(isLegal ? 0 : 1);

		if (!isLegal) {
			// 不合法，将磁盘上的文件删除
			newFile.delete();
		}

		response.setContentType(MediaType.TEXT_HTML_VALUE);
		return mapper.writeValueAsString(fileUploadResult);
	}

	private String getFilePath(String sourceFileName) {
		String baseFolder = PropertieService.REPOSITORY_PATH + File.separator + "images";
		Date nowDate = new Date();
		// yyyy/MM/dd
		String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator + new DateTime(nowDate).toString("MM") + File.separator
				+ new DateTime(nowDate).toString("dd");
		File file = new File(fileFolder);
		if (!file.isDirectory()) {
			// 如果目录不存在，则创建目录
			file.mkdirs();
		}
		// 生成新的文件名
		String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
		return fileFolder + File.separator + fileName;
	}

}
