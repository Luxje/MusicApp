package com.example.MusicApp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRequestDTO {

    @NotBlank
    @Size(max = 60)
    private String albumTitle;

    @NotBlank
    @Size(max = 30)
    private String albumGenre;

    @NotBlank
    @Size(max = 50)
    private String slug;

    @NotBlank
    @Size(max = 50)
    private String artistName;





}
