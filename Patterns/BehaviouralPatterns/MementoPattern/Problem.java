package BehaviouralPatterns.MementoPattern;
import java.util.*;


/*
assume resume editor where a user can make chnages to their resume= such as name, education or skill, ans may also want ability
to undo or redo changes. to do this we need a way to take a snapshot of the resume at any point of time and restore it later.
*/


// originator class: stoes the current state of resume

class ResumeEditor{
    String name;
    String education;
    String experience;
    List<String> skills;
}

// resume snapshot acts like a memento but isn't encapsulated properly

class ResumeSnapshot{
    public String name;
    public String education;
    public String experience;
    public List<String> skills;

    // constructor to create snapshot from resume editor
    public ResumeSnapshot(ResumeEditor editor){
        this.name=editor.name;
        this.education=editor.education;
        this.experience=editor.experience;
        this.skills=new ArrayList<>(editor.skills);
    } 

    // restore method to restore the state of resume editor from snapshot
    public void restore(ResumeEditor editor){
        editor.name=this.name;
        editor.education=this.education;
        editor.experience=this.experience;
        editor.skills=new ArrayList<>(this.skills); // deep copy to avoid shared reference
    }
}

public class Problem{
    public static void main(String[] args) {
        ResumeEditor editor=new ResumeEditor();
        editor.name="John Doe";
        editor.education="BSc Computer Science";
        editor.experience="2 years at XYZ Company";
        editor.skills=Arrays.asList("Java", "Python", "SQL");

        // take a snapshot of the current state of resume
        ResumeSnapshot snapshot=new ResumeSnapshot(editor);

        // make some changes to resume
        editor.name="John Smith";
        editor.education="MSc Computer Science";
        editor.experience="3 years at ABC Company";
        editor.skills=Arrays.asList("Java", "Python", "SQL", "C++");

        // restore the previous state of resume from snapshot
        snapshot.restore(editor);

        // print the restored state of resume
        System.out.println("Name: "+editor.name);
        System.out.println("Education: "+editor.education);
        System.out.println("Experience: "+editor.experience);
        System.out.println("Skills: "+editor.skills);
    }
}

/*
issues in the above code:
1. no caretaker role: the snapshot is created and managed directly by the client code, which can lead to tight coupling between the client and the memento. ideally, there should be a caretaker class that is responsible for creating and managing mementos, allowing the client to interact with the caretaker instead of directly with the memento.

2. no undo/redo stack: the current implementation only allows for a single snapshot to be taken and restored. to support multiple undo/redo operations, we would need to maintain a stack of mementos that can be pushed and popped as needed.

3. lack of encapsulation: the ResumeSnapshot class exposes its internal state through public fields, which violates the principle of encapsulation. ideally, the memento should be designed to hide its internal state and only provide methods for restoring the state to the originator.

4. tightly coupled snapshot and originator: the ResumeSnapshot class is tightly coupled to the ResumeEditor class, which makes it difficult to reuse the memento for other types of objects. ideally, the memento should be designed to be more generic and reusable across different types of originators.

5. no abstraction: the current implementation is specific to a resume editor, which limits its applicability to other contexts. ideally, the memento pattern should be designed to be more abstract and flexible, allowing it to be used in a variety of scenarios where state management is needed.

*/