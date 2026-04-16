const audio = document.getElementById('audio-player');
const playBtn = document.getElementById('play-btn');
const progressBar = document.getElementById('progress-bar');
const progressContainer = document.getElementById('progress-container');
const currentTimeEl = document.getElementById('current-time');
const durationEl = document.getElementById('total-duration');

function playFromData(element) {
    const trackId = element.getAttribute('data-id');
    const trackTitle = element.getAttribute('data-title');

    // Set audio source to your streaming endpoint
    audio.src = `/api/tracks/stream/${trackId}`;
    audio.play();

    // Update the player title
    document.querySelector('.title-1').innerText = trackTitle;
}

// Play/Pause function
playBtn.addEventListener('click', () => {
    if (audio.paused) {
        audio.play();
    } else {
        audio.pause();
    }
});

// Update Progress Bar
audio.addEventListener('timeupdate', () => {
    const { duration, currentTime } = audio;
    const progressPercent = (currentTime / duration) * 100;
    progressBar.style.width = `${progressPercent}%`;
    let curMins = Math.floor(currentTime / 60);
    let curSecs = Math.floor(currentTime % 60);
    if (curSecs < 10) curSecs = `0${curSecs}`;
    currentTimeEl.innerText = `${curMins}:${curSecs}`;
});

audio.addEventListener('loadedmetadata', () => {
    let durMins = Math.floor(audio.duration / 60);
    let durSecs = Math.floor(audio.duration % 60);
    if (durSecs < 10) durSecs = `0${durSecs}`;
    durationEl.innerText = `${durMins}:${durSecs}`;
});

progressContainer.addEventListener('click', (e) => {
    const width = progressContainer.clientWidth;
    const clickX = e.offsetX;
    audio.currentTime = (clickX / width) * audio.duration;
});