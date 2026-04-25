//package com.example.MusicApp.service;
//
//public class SpotifyService {
//    private final WebClient webClient;
//
//    public SpotifyService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://api.spotify.com/v1").build();
//    }
//
//    public Mono<String> getTrackMetadata(String trackId, String accessToken) {
//        return this.webClient.get()
//                .uri("/tracks/{id}", trackId)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//}
