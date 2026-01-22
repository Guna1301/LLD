package StructuralPatterns.BridgePattern;

interface PlayQuality {
    void play(String title);
}

class WebHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Playing " + title + " in Web HD quality.");
    }
}

class MobileHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Playing " + title + " in Mobile HD quality.");
    }
}

class SmartTVUltraHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Playing " + title + " in Smart TV Ultra HD quality.");
    }
}

class Web4KPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Playing " + title + " in Web 4K quality.");
    }
}



public class Problem {
    public static void main(String[] args) {
        PlayQuality player = new WebHDPlayer();
        player.play("Nature Documentary");
    }
}


/*
issues with this approach:in above code platform types(like web, mobile, smartv) are tightly
coupled with quality types(like HD, 4K, UltraHD). 
This leads to a combinatorial explosion of classes as new platforms and qualities are added.

if a new platfrom or quality is introduced, a new class must be created for each combination,
leading to class explosion and maintenance challenges.
if we have 5 platforms and 4 quality types, we would need to create 20 different classes to cover all combinations.

such tightly couled designs are hard to test, extend, and manage over time.
this is where the bridge pattern proves valuable by decoupling the abstraction(platform) from its implementation(quality),
allowing independent evolution of both dimensions.

*/