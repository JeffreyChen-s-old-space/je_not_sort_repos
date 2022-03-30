var audio;
var playing = false;
var audioIndex = 1;

function play(audioSrc) {
    if(!playing){
        audio = new Audio(audioSrc);
        playing=true;
        audio.play();
    }
    audio.onended = function() {
        playing = false;
    };
}
