function playFromData(element) {
    // Extract data from the attributes we set in Thymeleaf
    const trackId = element.getAttribute('data-id');
    const title = element.getAttribute('data-title');

    // Call your existing loading logic
    loadAndPlay(trackId, title);
}

function loadAndPlay(trackId, title) {
    const audio = document.getElementById('audio-player');
    const titleDisplay = document.querySelector('.title-1');

    // Update the source and UI
    audio.src = '/api/tracks/stream/' + trackId;
    if (titleDisplay) titleDisplay.innerText = title;

    audio.load();
    audio.play();
}