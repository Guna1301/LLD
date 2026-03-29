package BehaviouralPatterns.MementoPattern;
import java.util.*;

/*
memento pattern introduces three main components:
1. Originator: the object whose state we want to save and restore. 
    in our case its the resume editor which has fields like name, education, experience and skills.

2. Memento: the object that stores the snapshot of the originator's state.
    in our case its the resume snapshot which captures the state of the resume editor at a specific

3. caretaker: the object that manages the mementos and is responsible for saving and restoring the state of the originator.
    in our case its the ResumeHistory which creates snapshots and restores the resume editor's state.
*/

// originator class: stoes the current state of resume
class ResumeEditor{
    private String name;
    private String education;
    private String experience;
    private List<String> skills;

    // getters and setters for the fields
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void printResume(){
        System.out.println("Name: "+name);
        System.out.println("Education: "+education);
        System.out.println("Experience: "+experience);
        System.out.println("Skills: "+skills);
    }

    // save the current state of resume editor to a memento
    public ResumeMemento save(){
        return new ResumeMemento(name, education, experience, List.copyOf(skills));
    }

    // restore the state of resume editor from a memento
    public void restore(ResumeMemento memento){
        this.name=memento.getName();
        this.education=memento.getEducation();
        this.experience=memento.getExperience();
        this.skills=List.copyOf(memento.getSkills());
    }

    // inner memento class to encapsulate the state of resume editor
    public static class ResumeMemento{
        private final String name;
        private final String education;
        private final String experience;
        private final List<String> skills;

        // private constructor such that only the resume editor can create mementos and access its state
        private  ResumeMemento(String name, String education, String experience, List<String> skills){
            this.name=name;
            this.education=education;
            this.experience=experience;
            this.skills=skills;
        }

        public String getName() {
            return name;
        }

        public String getEducation() {
            return education;
        }

        public String getExperience() {
            return experience;
        }

        public List<String> getSkills() {
            return skills;
        }
    }
}


class ResumeHistory{
    private Stack<ResumeEditor.ResumeMemento> history;

    public ResumeHistory(){
        history=new Stack<>();
    }

    // save the current state of resume editor to history
    public void save(ResumeEditor editor){
        history.push(editor.save());
    }

    // restore the previous state of resume editor from history
    public void undo(ResumeEditor editor){
        if(!history.isEmpty()){
            ResumeEditor.ResumeMemento memento=history.pop();
            editor.restore(memento);
        }
    }
}

public class Solution{
    public static void main(String[] args) {
        ResumeEditor editor=new ResumeEditor();
        editor.setName("John Doe");
        editor.setEducation("BSc Computer Science");
        editor.setExperience("2 years at XYZ Company");
        editor.setSkills(Arrays.asList("Java", "Python", "SQL"));

        ResumeHistory history=new ResumeHistory();
        // print the Initial state of resume
        System.out.println("Initial Resume:");
        editor.printResume();
        System.out.println();

        // save the current state of resume
        history.save(editor);

        // make some changes to resume
        editor.setName("John Smith");
        editor.setEducation("MSc Computer Science");
        editor.setExperience("3 years at ABC Company");
        editor.setSkills(Arrays.asList("Java", "Python", "SQL", "C++"));

        // print the current state of resume
        System.out.println("Current Resume:");
        editor.printResume();

        // undo the changes and restore the previous state of resume
        history.undo(editor);

        // print the restored state of resume
        System.out.println("\nRestored Resume:");
        editor.printResume();
    }
}


/*
how this solves previous issues:

problem: no caretaker
solution: we have a separate caretaker class ResumeHistory that manages the mementos and is responsible for saving and restoring the state of the resume editor.

problem: only one level of undo
solution: we use a stack to store multiple mementos in the caretaker, allowing us to undo multiple changes.

problem: memento is not encapsulated properly
solution: we have an inner static class ResumeMemento inside the ResumeEditor class, which is private and can only be accessed by the ResumeEditor. This ensures that the memento is properly encapsulated and cannot be modified by external classes.

problem: tight coupling between memento and originator
solution: the memento is an inner class of the originator, which allows it to access the private fields of the originator without exposing them to external classes. This reduces coupling between the memento and the originator.


problem: snapshot logic spread across client code
solution: the logic for creating and restoring snapshots is encapsulated within the ResumeEditor class, which provides save() and restore() methods. This keeps the snapshot logic within the originator and prevents it from being spread across the client code.
*/


/*
memento pattern delegates the responsiblity of creating state snapshots to the actual ownet of the state,
i.e., the originator class. this allows the originator to have full control over how its state is captured and restored, and also ensures that the memento is properly encapsulated and cannot be modified by external classes. 
the caretaker class is responsible for managing the mementos and providing an interface for saving and restoring the state of the originator, but it does not need to know the details of how the mementos are created or restored.
this maintinas encapsulation and separation of concerns, and allows the memento pattern to be flexible and adaptable to different use cases.

*/