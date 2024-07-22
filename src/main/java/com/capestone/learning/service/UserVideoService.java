package com.capestone.learning.service;

import com.capestone.learning.entity.User;
import com.capestone.learning.entity.UserVideo;
import com.capestone.learning.repository.UserVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserVideoService {
    @Autowired
    UserVideoRepository userVideoRepository;

    public void saveUserVideoProgress(UserVideo userVideo){
        userVideo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());


        if (userVideoRepository.existsByUserIdAndVideoId(userVideo.getUsername(), userVideo.getVideoId())>0) {
            userVideoRepository.updateUserVideo(userVideo.getUsername(), userVideo.getVideoId(),
                    userVideo.getProgress()==100 ? true: false, userVideo.getProgress());
        } else {
            userVideoRepository.save(userVideo);
        }

    }
    public UserVideo getUserVideoProgress(UserVideo userVideo){
        userVideo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return userVideoRepository.findByUsernameAndVideoId(userVideo.getUsername(), userVideo.getVideoId());
    }
}
