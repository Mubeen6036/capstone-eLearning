package com.capestone.learning.service;

import com.capestone.learning.entity.Video;
import com.capestone.learning.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getVideos(UUID courseId){
        return videoRepository.findAllByCourseUuid(courseId);
    }

}
