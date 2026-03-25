package StructuralPatterns.BridgePattern;

interface VideoQuality{
    void load(String title);
}

class SDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Loading " + title + " in SD quality.");
    }
}

class HDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Loading " + title + " in HD quality.");
    }
}

class UltraHDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Loading " + title + " in Ultra HD quality.");
    }
}


abstract class VideoPlayer{
    protected VideoQuality quality;

    public VideoPlayer(VideoQuality quality) {
        this.quality = quality;
    }

    public abstract void play(String title);
}


class WebPlayer extends VideoPlayer{
    public WebPlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {
        quality.load(title);
        System.out.println("Playing " + title + " on Web platform.");
    }
}

class MobilePlayer extends VideoPlayer{
    public MobilePlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {
        quality.load(title);
        System.out.println("Playing " + title + " on Mobile platform.");
    }
}



public class Solution {
    public static void main(String[] args) {
        VideoPlayer player1 = new WebPlayer(new HDQuality());
        player1.play("Nature Documentary");

        VideoPlayer player2 = new MobilePlayer(new UltraHDQuality());
        player2.play("Action Movie");
    }
}


/*
how bridge pattern solves the issue:
- seperation of concerns: the platform and quality are decoupled into separate hierarchies.
- flexible combinations: new platforms and quality types can be added independently without creating a new class for each combination.
- easire to extend: adding a new platform or quality type only requires creating a new class in its respective hierarchy.
- cleaner code structure: the code is more organized and easier to maintain, as changes in one dimension do not affect the other.
*/
