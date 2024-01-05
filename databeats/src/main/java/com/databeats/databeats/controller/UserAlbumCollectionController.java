package com.databeats.databeats.controller;

import com.databeats.databeats.model.UserAlbumCollectionView;
import com.databeats.databeats.service.UserAlbumCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userAlbumCollection")
public class UserAlbumCollectionController {

    @Autowired
    private UserAlbumCollectionService userAlbumCollectionService;

    @GetMapping("/view")
    public List<UserAlbumCollectionView> getUserAlbumCollectionView() {
        return userAlbumCollectionService.getUserAlbumCollectionView();
    }
}
