package com.car.service.impl;

import com.car.mapper.CommentMapper;
import com.car.pojo.Comment;
import com.car.pojo.Page;
import com.car.service.CommentService;
import com.car.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    /**
     * 发表评论
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        // 补全属性
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentTime(DateUtils.newDate("yyyy-MM-dd"));
        commentMapper.addComment(comment);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Comment> getCommentList(Page<Comment> page) {
        // 设置当前页，如果当前页为空，默认是0
        Integer currentPage = page.getCurrentPage();
        if(currentPage==null) {
            currentPage=1;
        }
        // 设置每页显示条数，从resource文件中读取
        Integer currentCount = CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        // 计算索引，index=（当前页-1）*每页条数
        int index = (currentPage-1)*currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        // 根据这些信息，分页查询
        List<Comment> commentList = commentMapper.getCommentList(page);
        // 查询总条数
        Integer totalCount = commentMapper.getCommentCount(page);
        // 设置总条数
        page.setTotalCount(totalCount);
        // 设置数据
        page.setList(commentList);
        // 计算总页数，总页数=总条数/每页显示条数 向上取整
        int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
        page.setTotalPage(totalPage);
        return page;
    }

    /**
     * 根据id删除
     * @param commentId
     */
    @Override
    public void deleteCommentById(String commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    /**
     * 根据id获取
     * @param commentId
     * @return
     */
    @Override
    public Comment getById(String commentId) {
        return commentMapper.getById(commentId);
    }

    /**
     * 修改评论内容
     * @param comment
     */
    @Override
    public void updateCommentArticle(Comment comment) {
        commentMapper.updateCommentArticle(comment);
    }
}
