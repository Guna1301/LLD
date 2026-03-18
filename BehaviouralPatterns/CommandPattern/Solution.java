package BehaviouralPatterns.CommandPattern;

import java.util.*;

class Light{
    public void on(){
        System.out.println("Light is ON");
    }

    public void off(){
        System.out.println("Light is OFF");
    }
}

class AC{
    public void on(){
        System.out.println("AC is ON");
    }

    public void off(){
        System.out.println("AC is OFF");
    }
}

interface Command{
    void execute();
    void undo();
}

class LightOnCommand implements Command{
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command{
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class ACOnCommand implements Command{
    private AC ac;

    public ACOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements Command{
    private AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}


class RemoteControl{
    private Command buttons[] = new Command[4]; // 4 slots for commands
    private Stack<Command> commandHistory = new Stack<>();

    public void setCommand(int slot, Command command) {
        if (slot >= 0 && slot < buttons.length) {
            buttons[slot] = command;
        }
    }

    public void pressButton(int slot) {
        if (slot >= 0 && slot < buttons.length && buttons[slot] != null) {
            buttons[slot].execute();
            commandHistory.push(buttons[slot]);
        }else{
            System.out.println("No command assigned to this slot "+slot);
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No command to undo");
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();

        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, new LightOnCommand(light));
        remote.setCommand(1, new LightOffCommand(light));
        remote.setCommand(2, new ACOnCommand(ac));
        remote.setCommand(3, new ACOffCommand(ac));

        remote.pressButton(0); // Light ON
        remote.pressButton(2); // AC ON
        remote.pressUndo(); // AC OFF
        remote.pressButton(1); // Light OFF
        remote.pressUndo(); // Light ON
    }
}


/*
how this solution addresses the problem:

problem: Tight coupling
solution: by using the command pattern, the remotecontrol no longer interacts with the devices. 
          it only interacts with the command objects, which encapsulate the actions to be performed on the devices. this decouples the remote control from the specific implementations of the devices, allowing for greater flexibility and maintainability.

problem: lack of flexibility
solution: with command pattern, we can easily add new commands without modifying the existing code. we can create new command classes that implement the Command interface and set them to the remote control without changing the remote control's code.

problem: undo functionality
solution: the command pattern allows us to implement undo functionality by keeping a history of executed commands. 
          each command object can implement an undo method that reverses the action performed by the execute method. this allows for more complex undo functionality and support for multiple levels of undo.

problem: hardcoded commands
solution: the command pattern allows us to set commands dynamically at runtime. we can create new command objects and assign them to the remote control without modifying the remote control's code, making it more flexible and extensible.

problem: maintining command history
solution: by using a stack to keep track of executed commands, we can easily implement undo functionality. each time a command is executed, it is pushed onto the stack.
           when the undo button is pressed, the last command is popped from the stack and its undo method is called, allowing for multiple levels of undo and more complex undo functionality.

*/