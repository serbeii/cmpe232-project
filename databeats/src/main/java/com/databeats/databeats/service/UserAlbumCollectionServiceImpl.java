package com.databeats.databeats.service;

import com.databeats.databeats.model.UserAlbumCollectionView;
import com.databeats.databeats.repository.UserAlbumCollectionRepository;
import com.databeats.databeats.service.UserAlbumCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAlbumCollectionServiceImpl implements UserAlbumCollectionService {

    @Autowired
    private UserAlbumCollectionRepository userAlbumCollectionRepository;

    @Override
    public List<UserAlbumCollectionView> getUserAlbumCollectionView() {
        return userAlbumCollectionRepository.getUserAlbumCollectionView();
    }
}
