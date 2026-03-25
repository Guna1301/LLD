package BehaviouralPatterns.CommandPattern;

class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

class AC {
    public void on() {
        System.out.println("AC is ON");
    }

    public void off() {
        System.out.println("AC is OFF");
    }
}


class NavieRemoteControl{
    private Light light;
    private AC ac;
    private String lastAction = "";

    public NavieRemoteControl(Light light, AC ac) {
        this.light = light;
        this.ac = ac;
    }

    public void pressLightOn() {
        light.on();
        lastAction = "lightOn";
    }

    public void pressLightOff() {
        light.off();
        lastAction = "lightOff";
    }

    public void pressACOn() {
        ac.on();
        lastAction = "acOn";
    }

    public void pressACOff() {
        ac.off();
        lastAction = "acOff";
    }

    public void pressUndo() {
        switch (lastAction) {
            case "lightOn":
                light.off();
                lastAction = "";
                break;
            case "lightOff":
                light.on();
                lastAction = "";
                break;
            case "acOn":
                ac.off();
                lastAction = "";
                break;
            case "acOff":
                ac.on();
                lastAction = "";
                break;
            default:
                System.out.println("No action to undo");
        }
    }
}

public class Problem {
    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();
        NavieRemoteControl remoteControl = new NavieRemoteControl(light, ac);

        remoteControl.pressLightOn();
        remoteControl.pressACOn();

        remoteControl.pressUndo(); // Undo AC On
        remoteControl.pressUndo(); // Undo Light On
    }
}


/*
issues with this implementation:

1. Tight Coupling: The NavieRemoteControl class is tightly coupled with the Light and AC classes. If we want to add more devices (like TV, Fan, etc.), we would need to modify the NavieRemoteControl class, which violates the Open/Closed Principle.
2. lack of flexibility: the commands are hardcoded into the NavieRemoteControl class, making it difficult to add new commands or change existing ones without modifying the class.
3. undo functionality: undo is tightly coupled with the commands, making it difficult to implement more complex undo functionality or support multiple levels of undo.
4. hardcoded commands: the commands are hardcoded into the NavieRemoteControl class, which makes it difficult to reuse the command logic in other contexts or with different devices.
5. scalability issues: as the number of devices and commands increases, the NavieRemoteControl class will become more complex and harder to maintain, as it will need to handle all the different commands and their undo logic within a single class.
6. maintaing command history: the current implementation only allows for undoing the last command, which limits the functionality and does not allow for maintaining a history of commands that can be undone or redone.
7. violation of Single Responsibility Principle: the NavieRemoteControl class is responsible for both executing commands and managing the undo functionality, which violates the Single Responsibility Principle. This can lead to a class that is difficult to maintain and extend as it has multiple responsibilities.

*/