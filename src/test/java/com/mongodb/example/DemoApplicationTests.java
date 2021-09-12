package com.mongodb.example;

import com.mongodb.example.pojo.Comment;
import com.mongodb.example.service.CommentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    //注入Service
    @Autowired
    private CommentService commentService;

    /**
     * 保存一个评论
     */
    @Test
    public void testSaveComment(){
        Comment comment=new Comment();
        comment.setArticleid("100001");
        comment.setContent("测试添加的数据");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1004");
        comment.setNickname("拿破仑大帝");
        comment.setState("2");
        comment.setLikenum(0);
        comment.setReplynum(0);

        commentService.saveComment(comment);

        System.out.println("写入数据成功");
    }


    /**
     * 查询所有数据
     */
    @Test
    public void testFindAll(){
        List<Comment> list = commentService.findCommentList();
        System.out.println(list);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindCommentById(){
        Comment comment = commentService.findCommentById("613db23a3fc41d40e5fbbed7");
        System.out.println(comment);
    }

    /**
     * 测试根据父id查询子评论的分页列表
     */
    @Test
    public void testFindCommentListPageByParentid(){
        Page<Comment> pageResponse = commentService.findCommentListPageByParentid("3", 1, 2);
        System.out.println("----总记录数："+pageResponse.getTotalElements());
        System.out.println("----当前页数据："+pageResponse.getContent());
    }


    /**
     * 点赞数+1
     */
    @Test
    public void testUpdateCommentLikenumThumbup(){
        //对3号文档的点赞数+1
        commentService.updateCommentLikenumThumbup("613db23a3fc41d40e5fbbed7");
    }

}
