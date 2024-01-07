package com.databeats.databeats.dto;

import java.util.List;

public class AlbumBody {
        AlbumDTO albumDTO;

        List<SongDTO> songDTO;

        public AlbumBody() {
        }

		public AlbumDTO getAlbumDTO() {
			return albumDTO;
		}
		public void setAlbumDTO(final AlbumDTO albumDTO) {
			this.albumDTO = albumDTO;
		}
		public List<SongDTO> getSongDTO() {
			return songDTO;
		}
		public void setSongDTO(final List<SongDTO> songDTO) {
			this.songDTO = songDTO;
		}
        

    }
