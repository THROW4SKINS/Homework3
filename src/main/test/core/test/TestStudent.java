package core.test;

import org.junit.Before;
import org.junit.Test;

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

import static org.junit.Assert.*;

public class TestStudent {
	
	private IAdmin admin;
	private IInstructor instructor;
    private IStudent student;
    private IStudent student2;
    
    @Before
    public void setup() {

        this.instructor = new Instructor();
        this.admin = new Admin();
        this.student = new Student();
        this.student2 = new Student();
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw");
    }
    
    @Test
    public void testRegisterForClass(){
        this.student.registerForClass("A", "Test", 2017);
        assertTrue(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void testRegisterForClass2(){
        this.student.registerForClass("A", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("B", "Test", 2017));
    }
    
    @Test
    public void testRegisterForClass3(){
        this.student.registerForClass("A", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("A", "Test", 2016));
    }
    
    @Test
    public void testRegisterForClass4(){
        this.student.registerForClass("A", "Test", 2017);
        this.student2.registerForClass("B", "Test", 2017);
        assertFalse(this.student2.isRegisteredFor("B", "Test", 2017));
    }
    
    @Test
    public void testRegisterForClass5(){
        this.student.registerForClass("A", "Test1", 2017);
        assertFalse(this.student2.isRegisteredFor("A", "Test1", 2017));
    }
    
    @Test
    public void testSubmitHomework(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.submitHomework("A", "hw", "key", "Test", 2017);
        assertTrue(this.student.hasSubmitted("A", "hw","Test", 2017));
    }
    
    @Test
    public void testSubmitHomework2(){
        this.student.registerForClass("A", "Test", 2017);
      //  this.student.submitHomework("A", "hw", "key", "Test", 2017);
        assertFalse(this.student.hasSubmitted("A", "hw","Test", 2017));
    }
    
    @Test
    public void testSubmitHomework3(){
        //this.student.registerForClass("A", "Test", 2017);
        this.student.submitHomework("A", "hw", "key", "Test", 2017);
        assertFalse(this.student.hasSubmitted("A", "hw","Test", 2017));
    }
    
    @Test
    public void testSubmitHomework4(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.submitHomework("A", "wronghw", "key", "Test", 2017);
        assertFalse(this.student.hasSubmitted("A", "wronghw","Test", 2017));
    }
    
    @Test
    public void testSubmitHomework5(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.submitHomework("A", "hw", null, "Test", 2017);
        assertFalse(this.student.hasSubmitted("A", "hw","Test", 2017));
    }
    
    @Test
    public void testDropClass(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.dropClass("A", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void testDropClass2(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.dropClass("A", "Test", 2016);
        assertTrue(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void testDropClass3(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.dropClass("B", "Test", 2017);
        assertTrue(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void testDropClass4(){
        this.student.registerForClass("A", "Test", 2017);
        this.student.dropClass("A", "Test1", 2017);
        assertTrue(this.student.isRegisteredFor("A", "Test", 2017));
    }
}
