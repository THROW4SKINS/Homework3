package core.test;

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestInstructor {
	private IAdmin admin;
	private IInstructor instructor;
    private IStudent student;
    
    @Before
    public void setup() {

        this.instructor = new Instructor();
        this.admin = new Admin();
        this.student = new Student();
        this.admin.createClass("Test", 2017, "Instructor", 10);

    }
    
    @Test
    public void testaddHomework(){
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
        assertTrue(this.instructor.homeworkExists("Test", 2017, "hw"));
    }
    
    @Test
    public void testaddHomework2(){
        this.instructor.addHomework("wrong", "Test", 2017, "hw");
        assertFalse(this.instructor.homeworkExists("Test", 2017, "hw"));
    }
    
    @Test
    public void testaddHomework3(){
        this.instructor.addHomework("Instructor", "Test", 2017, "");
        assertFalse(this.instructor.homeworkExists("Test", 2017, ""));
    }
    
    @Test
    public void testaddHomework4(){
        this.instructor.addHomework("Instructor", "Test", 2016, "hw");
        assertFalse(this.instructor.homeworkExists("Test", 2017, "hw"));
    }
    
    @Test
    public void testAssignGrade(){
    	
        this.student.registerForClass("A", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
        this.student.submitHomework("A", "hw","key","Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "hw", "A", 100);
        assertTrue(100 == this.instructor.getGrade("Test",2017,"hw","A"));
    }
    
    @Test
    public void testAssignGrade2(){
    	
        this.student.registerForClass("A", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
        this.student.submitHomework("A", "hw","key","Test", 2017);
        this.instructor.assignGrade("Instructor101", "Test", 2017, "hw", "A", 100);
        assertFalse(100 == this.instructor.getGrade("Test",2017,"hw","A"));
    }
    
    @Test
    public void testAssignGrade3(){
    	
        this.student.registerForClass("A", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
        this.student.submitHomework("A", "hw","key","Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "hw", "A", -100);
        assertFalse(-100 == this.instructor.getGrade("Test",2017,"hw","A"));
    }
    
    @Test
    public void testAssignGrade4(){
    	
        this.student.registerForClass("A", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
       // this.student.submitHomework("A", "hw","key","Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "hw", "A", 100);
        assertNull(this.instructor.getGrade("Test",2017,"hw","A"));
    }
}

