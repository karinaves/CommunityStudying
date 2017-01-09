package com.tau.commstudy.entities.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Comment;

@Transactional
public interface CommentDao extends CrudRepository<Comment, Long> {
    public List<Comment> findByPost_IdOrderByIsAcceptedDescAnswerRateDesc(Long postId);

}
