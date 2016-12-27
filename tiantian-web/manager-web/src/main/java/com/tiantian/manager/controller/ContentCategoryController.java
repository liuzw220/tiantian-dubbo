package com.tiantian.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.item.apis.ContentCategoryService;
import com.tiantian.item.bo.ContentCategoryBo;
import com.tiantian.item.vo.ContentCategoryVo;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired(required=false)
    private ContentCategoryService contentCategoryService;

    
    
    /**
     * rest风格的接口， 查询所有的内容分类菜单
     * @return 返回http状态如果是200返回需要的数据
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ContentCategoryVo>> queryAll(@RequestParam(value="id",defaultValue="0")Long pid){
        try {
        	System.out.println("=====================pid="+pid);
            ContentCategoryBo cc=new ContentCategoryBo();
            cc.setParentId(pid);
            List<ContentCategoryVo> queryAll = contentCategoryService.queryList(cc);
            System.out.println("=====================pid="+queryAll.toString());
            //200
            return ResponseEntity.ok(queryAll);
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /**
     * 增加内容分类菜单
     * @param contentCategory 内容分类 对象
     * @return  返回http状态，如果新增成功返回201，错误返回500
     */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> saveContentCategory(ContentCategoryBo contentCategory){
        try {
            contentCategoryService.save(contentCategory);
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
     * 增加内容分类菜单
     * @param contentCategory 内容分类 对象
     * @return  返回http状态，如果新增成功返回204(跟新成功,但没有数据返回)，错误返回500
     */
    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateContentCategory(ContentCategoryBo contentCategory){
        try {
            contentCategoryService.updateSelective(contentCategory);
            //204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    /**
     * 增加内容分类菜单
     * @param contentCategory 内容分类 对象
     * @return  返回http状态，如果新增成功返回204(跟新成功,但没有数据返回)，错误返回500
     */
    @RequestMapping(method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteContentCategory(@RequestParam(value="parentId",required=false) Long parentId, 
            @RequestParam("id") Long id){
        try {
            contentCategoryService.deleteById(id);
            //203
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
