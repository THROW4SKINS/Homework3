package core.test;

import core.api.IAdmin;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {
	
	private IAdmin admin;
	private IAdmin admin1;
	private IAdmin admin2;
	private IStudent student;
	private IStudent student2;
   

    @Before
    public void setup() {
        this.admin = new Admin();
        this.admin1 = new Admin();
        this.admin2 = new Admin();
        this.student = new Student();
        this.student2 = new Student();
        
        
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    
    @Test
    public void testMakeClassClassname(){
        this.admin.createClass("Test", 2017, "instructor", 10);
        assertFalse(this.admin.classExists("Test101",2017));
    }

    @Test
    public void testMakeClassGetInstrName() {
        String name;
        this.admin.createClass("Test", 2017, "A", 10);
        this.admin.createClass("Test", 2017, "B", 10);
        name = this.admin.getClassInstructor("Test", 2017);
        assertTrue(name.equals(this.admin.getClassInstructor("Test", 2017)));
    }
    
    @Test
    public void testMakeClassCreateClass(){
        this.admin.createClass("A",2017,"Instructor", 10);
        this.admin1.createClass("B", 2017, "Instructor", 10);
        this.admin2.createClass("C", 2017, "Instructor", 10);
        assertFalse(this.admin2.classExists("C", 2017));
    }
    
    @Test
    public void testMakeClassCreateClass2(){
        this.admin.createClass("A",2017,null, 10);
        assertFalse(this.admin.classExists("A", 2017));
    }
    
    @Test
    public void testMakeClassCreateClass3(){
        this.admin.createClass("A",2017,"AA", 10);
        assertFalse(this.admin.classExists("C", 2017));
    }
    
    
    /*@param capacity Maximum capacity of this class > 08*/
    @Test
    public void testCapacity(){
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testCapacity2() {
        this.admin.createClass("Test", 2017, "instructor", 10);
        assertTrue(this.admin.classExists("Test", 2017));
    }
    
    @Test
    public void testCapacity3(){
        this.admin.createClass("Test", 2017, "instructor", 10);
        assertEquals(-1, this.admin.getClassCapacity("null", 2017));
    }
    
    @Test
    public void testChangeCapacity(){
        this.admin.createClass("Test", 2017, "instructor", 2);
        this.student.registerForClass("A", "Test", 2017);
        this.student2.registerForClass("A", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 1);
        int cap;
        cap = this.admin.getClassCapacity("Test", 2017);
        assertFalse(2 != cap);
    }
    
}
