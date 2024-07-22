package com.capestone.learning.repository;

import com.capestone.learning.entity.UserVideo;
import com.capestone.learning.entity.Video;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserVideoRepository extends CassandraRepository<UserVideo, String> {
    @Query("SELECT COUNT(*) FROM capstone_learning.user_videos WHERE username = ?0 AND videoid = ?1")
    int existsByUserIdAndVideoId(String userId, UUID videoId);

    @Query("UPDATE capstone_learning.user_videos SET watched = :watched, progress = :progress WHERE username = :userId AND videoid = :videoId")
    void updateUserVideo(String userId, UUID videoId, boolean watched, int progress);

    void deleteByUsername(String username);

    UserVideo findByUsernameAndVideoId(String username, UUID videoId);

    @Query("SELECT * FROM capstone_learning.user_videos WHERE username = ?0 AND videoid IN ?1")
    List<UserVideo> findByUserIdAndVideoIds(String userId, List<UUID> videoIds);

    @Query("DELETE FROM capstone_learning.user_videos WHERE username = ?0 AND videoid IN ?1")
    void deleteByUsernameAndVideoIds(String username, List<UUID> videoIds);
}
