package com.capestone.learning.response;

import java.util.UUID;

public class VideoStatus {
    private UUID videoId;
    private String videoTitle;
    private String videoUrl;
    private UUID courseUuid;
    private int position;

    // Getters and setters
    public UUID getVideoId() {
        return videoId;
    }

    public void setVideoId(UUID videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UUID getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(UUID courseUuid) {
        this.courseUuid = courseUuid;
    }

    public int getPosition() {
        return position;
    }

    private String username;
    private boolean watched;
    private int progress;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
