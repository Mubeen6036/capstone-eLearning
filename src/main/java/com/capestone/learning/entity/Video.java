package com.capestone.learning.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("videos")
public class Video {
    @PrimaryKey
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

    public void setPosition(int position) {
        this.position = position;
    }
}
