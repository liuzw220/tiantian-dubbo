package com.tiantian.manager.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiantian.item.apis.ContentService;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.vo.ContentVo;

@Controller
@RequestMapping("content")
public class ContentController {

	 @Autowired(required=false)
    private ContentService  contentService;

    /**
     * 通过内容目录id查找内容
     * @param categoryId 内容类目ID
     * @param page 当前页码
     * @param rows 页面大小
     * @return 返回http状态 如果成功200(内容目录结果集)，否则返回500
     */
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ContentVo>> queryContentList(@RequestParam(value="categoryId",defaultValue="0")Long categoryId,
            @RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows){
        try {
            ContentBo content=new ContentBo();
            content.setCategoryId(categoryId);
            //200
            content.setPageIndex(page);
            content.setPageSize(rows);
            return ResponseEntity.ok(contentService.queryListPage(content).getRows());
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 添加内容内容
     * @param content
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveContent(ContentBo content){
        try {
            contentService.save(content);
            //201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * 添加内容内容
     * @param content
     * @return
     */
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> updateContent(ContentBo content){
        try {
            Collection<?> cons=new ArrayList<String>();
            Collections con=null;
            List<?> list=new ArrayList<String>();
            this.contentService.update(content);
            //201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 添加内容内容
     * @param content
     * @return
     */
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContentById(@RequestParam(value="ids",required=false)Long[] ids){
        try {
            if(ids==null) 
                //406(参数不合法)
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            this.contentService.deleteByIds(ids);
            //201
            return ResponseEntity.status(HttpStatus.CREATED).build(); 
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
