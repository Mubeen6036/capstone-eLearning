package com.capestone.learning.service;

import com.capestone.learning.entity.Course;
import com.capestone.learning.entity.UserVideo;
import com.capestone.learning.entity.Video;
import com.capestone.learning.repository.CourseRepository;
import com.capestone.learning.repository.RegistrationRepository;
import com.capestone.learning.repository.UserVideoRepository;
import com.capestone.learning.repository.VideoRepository;
import com.capestone.learning.response.VideoStatus;
import com.dtsx.astra.sdk.org.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDataService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserVideoRepository userVideosRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private VideoRepository videoRepository;

    public void removeUserData(UUID courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Remove from registrations table
        registrationRepository.deleteByUsernameAndCourseId(username, courseId);

        List<Video> videos = videoRepository.findAllByCourseUuid(courseId);
        List<UUID> videoIds = videos.stream().map(x->x.getVideoId()).collect(Collectors.toList());
        userVideosRepository.deleteByUsernameAndVideoIds(username, videoIds);
        // Remove from user_videos table
        courseRepository.updateUserVideo(courseId);
    }

    public List<VideoStatus>  retrieveCourseInformation(UUID courseId) {
        List<Video> videos = videoRepository.findAllByCourseUuid(courseId);
        return getUserVideosProgress(videos);
    }

    public List<VideoStatus> getUserVideosProgress(List<Video> vidoes){
        List<VideoStatus> userStatuses = new ArrayList<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        for(Video video: vidoes){
            VideoStatus videoStatus = new VideoStatus();
            videoStatus.setCourseUuid(video.getCourseUuid());
            videoStatus.setVideoUrl(video.getVideoUrl());
            videoStatus.setVideoTitle(video.getVideoTitle());
            videoStatus.setVideoId(video.getVideoId());
            videoStatus.setUsername(username);
            UserVideo userVideo = userVideosRepository.findByUsernameAndVideoId(videoStatus.getUsername(), videoStatus.getVideoId());
            videoStatus.setWatched(userVideo!=null?userVideo.isWatched():Boolean.FALSE);
            videoStatus.setProgress(userVideo!=null?userVideo.getProgress():0);
            userStatuses.add(videoStatus);
        }
        return userStatuses;
    }

    public UserVideo getUserVideoProgress(UserVideo userVideo){
        userVideo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return userVideosRepository.findByUsernameAndVideoId(userVideo.getUsername(), userVideo.getVideoId());
    }

    public void removeUserData(UUID courseId, String username) {
        // Remove from registrations table
        registrationRepository.deleteByUsernameAndCourseId(username, courseId);

        List<Video> videos = videoRepository.findAllByCourseUuid(courseId);
        List<UUID> videoIds = videos.stream().map(x->x.getVideoId()).collect(Collectors.toList());
        userVideosRepository.deleteByUsernameAndVideoIds(username, videoIds);
        // Remove from user_videos table
        courseRepository.updateUserVideo(courseId);
    }
}
