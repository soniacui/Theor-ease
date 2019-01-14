import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;
import java.lang.Math.abs;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
/*
Summative - Major Programming Project

Author: Sonia Cui
Date: January 23, 2018
For: Mr. Berry
Course: ICS4U

Functions:
- draw 4 different shapes of 4 different colours
- chosen by the user via radio buttons
- dimensions changeable via text boxes
- can animate left to right or in a figure-8 pattern
- changed speed between slow/medium/fast
- reset to blank screen

Input: The user can click radio buttons (checkboxes), enter values into text fields, and click various buttons, such as 'draw', 'animation 1', and 'reset'.
Output: When the user clicks 'draw', the program will render a shape with the corresponding attributes (shape, colour, size) based on their radio button choices and dimensions entered
in the text fields. The program will animate the selected shape across the screen (for the animate1 button) or in a figure-8 pattern (for the animate2 button). The program will also change
the speed of the animating shape (from slow/med/fast)if the 'change speed' button is clicked, and will reset to a blank screen if the 'reset' button is clicked.

*/

public class Summative extends Applet
    implements ActionListener
{
    boolean menu = false; //boolean to keep track of if user is at the menu
    boolean welcome = true; //boolean to keep track of user is at welcome screen
    boolean quizpressed = false; //boolean to keep track of if user is at the quiz
    boolean correct = false; //boolean to keep track of if user's answer is correct
    boolean pageChanged = true; //boolean to keep track of if page has changed

    Button begin = new Button ("BEGIN"); //initialize 'begin' button

    Button lesson1 = new Button ("Lesson 1 - Introduction to Reading Notes"); //initialize lesson buttons
    Button lesson2 = new Button ("Lesson 2 - Counting");
    Button lesson3 = new Button ("Lesson 3 - Time Signatures");
    Button lesson4 = new Button ("Lesson 4 - Key Signatures");
    Button lesson5 = new Button ("Lesson 5 - Scales");
    Button lesson6 = new Button ("Lesson 6 - Intervals");
    Button lesson7 = new Button ("Lesson 7 - Chords");

    Button mainmenu = new Button ("Go to Main Menu"); //initialize main menu, lesson, and quiz buttons
    Button lesson = new Button ("Go to Lesson");
    Button quiz = new Button ("Go to Quiz");

    Button twinkle1 = new Button ("twinkle1"); //initialize all buttons that will play audio
    Button twinkle2 = new Button ("twinkle2");
    Button cScale1 = new Button ("cscale1");
    Button cScale = new Button ("cscale");
    Button gScale = new Button ("gscale");
    Button dScale = new Button ("dscale");
    Button aScale = new Button ("ascale");
    Button eScale = new Button ("escale");
    Button bScale = new Button ("bscale");
    Button fshScale = new Button ("fsharpscale");
    Button cshScale = new Button ("csharpscale");
    Button fScale = new Button ("fscale");
    Button bflScale = new Button ("bflatscale");
    Button eflScale = new Button ("eflatscale");
    Button aflScale = new Button ("aflatscale");
    Button dflScale = new Button ("dflatscale");
    Button gflScale = new Button ("gflatscale");
    Button cflScale = new Button ("cflatscale");
    Button major2nd = new Button ("major2nd");
    Button major3rd = new Button ("major3rd");
    Button per4th = new Button ("perfect4th");
    Button per5th1 = new Button ("perfect5th1");
    Button per5th = new Button ("perfect5th");
    Button major6th = new Button ("major6th");
    Button major7th = new Button ("major7th");
    Button per8th1 = new Button ("perfect8th1");
    Button per8th = new Button ("perfect8th");
    Button rootPos = new Button ("rootpos");
    Button rootPos1 = new Button ("rootpos1");
    Button rootPos2 = new Button ("rootpos2");
    Button firstInver = new Button ("firstInversion");
    Button secondInver = new Button ("secondInversion");

    Button nextPage = new Button ("Next"); //initialize next and previous buttons
    Button prevPage = new Button ("Previous");

    Button answer1 = new Button ("1"); //initialize answer buttons
    Button answer2 = new Button ("2");
    Button answer3 = new Button ("3");
    Button answer4 = new Button ("4");

    int lessonCount = 0; //keeps track of what lesson the user is on
    int correctAnswer = 0; //keeps track of the correct answer
    int userAnswer = 0; //keeps track of the user's answer
    int qrunning = 1; //keeps track of the current question number
    int correctAns1 = 0; //keeps track of the correct answer for the corresponding question
    int correctAns2 = 0;
    int correctAns3 = 0;
    int correctAns4 = 0;
    int correctAns5 = 0;
    int page = 1; //keeps track of the page number of the lesson

    Image welBG, lessons; //initialize welcome menu and lesson menu images
    //initialize all pictures for lessons
    Image lesson11, lesson12, lesson13, lesson21, lesson22, lesson23, lesson31, lesson32, lesson33, lesson41, lesson42, lesson43, lesson51, lesson52, lesson53, lesson61, lesson62, lesson63, lesson71, lesson72, lesson73;
    Image question1image, question2image, question3image, question4image, question5image; //initialize images for quizzes

    AudioClip twinkle1a, twinkle2a; //initialize rhythm example audio
    AudioClip cScalea, gScalea, dScalea, aScalea, eScalea, bScalea, fshScalea, cshScalea, fScalea, bflScalea, eflScalea, aflScalea, dflScalea, gflScalea, cflScalea; //initialize scale audio
    AudioClip major2nda, major3rda, per4tha, per5tha, major6tha, major7tha, per8tha; //initialize interval audio
    AudioClip rootPosa, firstInvera, secondInvera; //initialize chord audio

    public void init ()
    {
	begin.addActionListener (this);
	begin.setBackground (Color.orange);
	add (begin);
	lesson1.addActionListener (this); //add action listener for all lesson buttons
	lesson2.addActionListener (this);
	lesson3.addActionListener (this);
	lesson4.addActionListener (this);
	lesson5.addActionListener (this);
	lesson6.addActionListener (this);
	lesson7.addActionListener (this);

	twinkle1.addActionListener (this); //add action listener for all audio buttons
	twinkle2.addActionListener (this);
	cScale.addActionListener (this);
	cScale1.addActionListener (this);
	gScale.addActionListener (this);
	dScale.addActionListener (this);
	aScale.addActionListener (this);
	eScale.addActionListener (this);
	bScale.addActionListener (this);
	fshScale.addActionListener (this);
	cshScale.addActionListener (this);
	fScale.addActionListener (this);
	bflScale.addActionListener (this);
	eflScale.addActionListener (this);
	aflScale.addActionListener (this);
	dflScale.addActionListener (this);
	gflScale.addActionListener (this);
	cflScale.addActionListener (this);
	major2nd.addActionListener (this);
	major3rd.addActionListener (this);
	per4th.addActionListener (this);
	per5th.addActionListener (this);
	per5th1.addActionListener (this);
	major6th.addActionListener (this);
	major7th.addActionListener (this);
	per8th.addActionListener (this);
	per8th1.addActionListener (this);
	rootPos.addActionListener (this);
	rootPos1.addActionListener (this);
	rootPos2.addActionListener (this);
	firstInver.addActionListener (this);
	secondInver.addActionListener (this);

	mainmenu.addActionListener (this); //add action listener for main menu, lesson, and quiz buttons
	lesson.addActionListener (this);
	quiz.addActionListener (this);

	nextPage.addActionListener (this); //add action listener for next and previous page buttons
	prevPage.addActionListener (this);

	answer1.addActionListener (this); //add action listener for all answer buttons
	answer2.addActionListener (this);
	answer3.addActionListener (this);
	answer4.addActionListener (this);

	add (lesson1); //add lesson buttons
	add (lesson2);
	add (lesson3);
	add (lesson4);
	add (lesson5);
	add (lesson6);
	add (lesson7);

	add (mainmenu); //add main menu, lesson, and quiz buttons
	add (lesson);
	add (quiz);

	add (twinkle1); //add audio buttons
	add (twinkle2);
	add (cScale);
	add (cScale1);
	add (gScale);
	add (dScale);
	add (aScale);
	add (eScale);
	add (bScale);
	add (fshScale);
	add (cshScale);
	add (fScale);
	add (bflScale);
	add (eflScale);
	add (aflScale);
	add (dflScale);
	add (gflScale);
	add (cflScale);
	add (major2nd);
	add (major3rd);
	add (per4th);
	add (per5th);
	add (per5th1);
	add (major6th);
	add (major7th);
	add (per8th);
	add (per8th1);
	add (rootPos);
	add (rootPos1);
	add (rootPos2);
	add (firstInver);
	add (secondInver);

	add (nextPage); //add next and previous page buttons
	add (prevPage);

	add (answer1); //add answer buttons
	add (answer2);
	add (answer3);
	add (answer4);

	welBG = getImage (getDocumentBase (), "welcomebackground.png"); //import welcome screen background image
	lessons = getImage (getDocumentBase (), "lessons.png"); //import lesson menu background image
	lesson11 = getImage (getDocumentBase (), "lesson1.1.png"); //import lesson 1 images
	lesson12 = getImage (getDocumentBase (), "lesson1.2.png");
	lesson13 = getImage (getDocumentBase (), "lesson1.3.png");

	lesson21 = getImage (getDocumentBase (), "lesson2.1.png"); //import lesson 2 images
	lesson22 = getImage (getDocumentBase (), "lesson2.2.png");
	lesson23 = getImage (getDocumentBase (), "lesson2.3.png");

	lesson31 = getImage (getDocumentBase (), "lesson3.1.png"); //import lesson 3 images
	lesson32 = getImage (getDocumentBase (), "lesson3.2.png");
	lesson33 = getImage (getDocumentBase (), "lesson3.3.png");

	lesson41 = getImage (getDocumentBase (), "lesson4.1.png"); //import lesson 4 images
	lesson42 = getImage (getDocumentBase (), "lesson4.2.png");
	lesson43 = getImage (getDocumentBase (), "lesson4.3.png");

	lesson51 = getImage (getDocumentBase (), "lesson5.1.png"); //import lesson 5 images
	lesson52 = getImage (getDocumentBase (), "lesson5.2.png");
	lesson53 = getImage (getDocumentBase (), "lesson5.3.png");

	lesson61 = getImage (getDocumentBase (), "lesson6.1.png"); //import lesson 6 images
	lesson62 = getImage (getDocumentBase (), "lesson6.2.png");
	lesson63 = getImage (getDocumentBase (), "lesson6.3.png");

	lesson71 = getImage (getDocumentBase (), "lesson7.1.png"); //import lesson 7 images
	lesson72 = getImage (getDocumentBase (), "lesson7.2.png");
	lesson73 = getImage (getDocumentBase (), "lesson7.3.png");

	Font myFont = new Font ("Times New Roman", Font.BOLD, 24); //create a big, bold font for answer buttons
	answer1.setFont (myFont); //buttons to new font
	answer2.setFont (myFont);
	answer3.setFont (myFont);
	answer4.setFont (myFont);

	major2nda = getAudioClip (getDocumentBase (), "major2nd.wav"); //import all audio files
	major3rda = getAudioClip (getDocumentBase (), "major3rd.wav");
	per4tha = getAudioClip (getDocumentBase (), "perfect4th.wav");
	per5tha = getAudioClip (getDocumentBase (), "perfect5th.wav");
	major6tha = getAudioClip (getDocumentBase (), "major6th.wav");
	major7tha = getAudioClip (getDocumentBase (), "major7th.wav");
	per8tha = getAudioClip (getDocumentBase (), "perfect8th.wav");
	twinkle1a = getAudioClip (getDocumentBase (), "twinkle.wav");
	twinkle2a = getAudioClip (getDocumentBase (), "twinkle2.wav");
	cScalea = getAudioClip (getDocumentBase (), "cmajor.wav");
	gScalea = getAudioClip (getDocumentBase (), "gmajor.wav");
	dScalea = getAudioClip (getDocumentBase (), "dmajor.wav");
	aScalea = getAudioClip (getDocumentBase (), "amajor.wav");
	eScalea = getAudioClip (getDocumentBase (), "emajor.wav");
	bScalea = getAudioClip (getDocumentBase (), "bmajor.wav");
	fshScalea = getAudioClip (getDocumentBase (), "fsharpmajor.wav");
	cshScalea = getAudioClip (getDocumentBase (), "csharpmajor.wav");
	fScalea = getAudioClip (getDocumentBase (), "fmajor.wav");
	bflScalea = getAudioClip (getDocumentBase (), "bflatmajor.wav");
	eflScalea = getAudioClip (getDocumentBase (), "eflatmajor.wav");
	aflScalea = getAudioClip (getDocumentBase (), "aflatmajor.wav");
	dflScalea = getAudioClip (getDocumentBase (), "dflatmajor.wav");
	gflScalea = getAudioClip (getDocumentBase (), "gflatmajor.wav");
	cflScalea = getAudioClip (getDocumentBase (), "cflatmajor.wav");
	rootPosa = getAudioClip (getDocumentBase (), "rootpos.wav");
	firstInvera = getAudioClip (getDocumentBase (), "firstinversion.wav");
	secondInvera = getAudioClip (getDocumentBase (), "secondinversion.wav");
    }


    public void paint (Graphics g)
    {
	this.setSize (new Dimension (1150, 680)); //set default applet size

	this.begin.setLocation (510, 345);
	this.begin.setSize (140, 75);

	this.lesson1.setLocation (430, 220);
	this.lesson2.setLocation (430, 260);
	this.lesson3.setLocation (430, 300);
	this.lesson4.setLocation (430, 340);
	this.lesson5.setLocation (430, 380);
	this.lesson6.setLocation (430, 420);
	this.lesson7.setLocation (430, 460);

	this.lesson1.setSize (380, 35);
	this.lesson2.setSize (380, 35);
	this.lesson3.setSize (380, 35);
	this.lesson4.setSize (380, 35);
	this.lesson5.setSize (380, 35);
	this.lesson6.setSize (380, 35);
	this.lesson7.setSize (380, 35);

	this.mainmenu.setLocation (5, 5);
	this.quiz.setLocation (1050, 5);
	this.nextPage.setLocation (1050, 615);
	this.prevPage.setLocation (5, 615);
	this.lesson.setLocation (125, 5);
	this.answer1.setLocation (5, 250);
	this.answer2.setLocation (5, 300);
	this.answer3.setLocation (5, 350);
	this.answer4.setLocation (5, 400);
	this.twinkle1.setLocation (290, 185);
	this.twinkle2.setLocation (540, 505);
	this.cScale.setLocation (130, 505);
	this.cScale1.setLocation (845, 545);
	this.gScale.setLocation (570, 125);
	this.dScale.setLocation (570, 200);
	this.aScale.setLocation (570, 275);
	this.eScale.setLocation (570, 350);
	this.bScale.setLocation (570, 425);
	this.fshScale.setLocation (570, 505);
	this.cshScale.setLocation (570, 585);
	this.fScale.setLocation (960, 125);
	this.bflScale.setLocation (960, 200);
	this.eflScale.setLocation (960, 275);
	this.aflScale.setLocation (960, 350);
	this.dflScale.setLocation (960, 425);
	this.gflScale.setLocation (960, 505);
	this.cflScale.setLocation (960, 585);
	this.major2nd.setLocation (135, 230);
	this.major3rd.setLocation (135, 380);
	this.per4th.setLocation (135, 530);
	this.per5th.setLocation (675, 90);
	this.per5th1.setLocation (265, 380);
	this.major6th.setLocation (675, 240);
	this.major7th.setLocation (675, 390);
	this.per8th.setLocation (675, 525);
	this.per8th1.setLocation (825, 380);
	this.rootPos.setLocation (150, 405);
	this.rootPos1.setLocation (790, 440);
	this.rootPos2.setLocation (900, 405);
	this.firstInver.setLocation (400, 405);
	this.secondInver.setLocation (650, 405);
	if (welcome == true)
	{
	    welcome (g); //calls welcome method
	}
	else if (menu == true)
	{
	    lessonMenu (g); //calls lesson menu method
	}
	else if (lessonCount == 1)
	{
	    lessonCount = 1; //set lesson count to 1
	    lesson (g, lesson11, lesson12, lesson13); //calls lesson method
	    correctAns1 = 2; //set correct answer for this lesson's quiz
	    correctAns2 = 4;
	    correctAns3 = 1;
	    correctAns4 = 3;
	    correctAns5 = 1;
	    question1image = getImage (getDocumentBase (), "quiz1.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz1.2.png");
	    question3image = getImage (getDocumentBase (), "quiz1.3.png");
	    question4image = getImage (getDocumentBase (), "quiz1.4.png");
	    question5image = getImage (getDocumentBase (), "quiz1.5.png");
	}
	else if (lessonCount == 2)
	{
	    lessonCount = 2; //set lesson count to 2
	    lesson (g, lesson21, lesson22, lesson23); //calls lesson method
	    correctAns1 = 3; //set correct answer for this lesson's quiz
	    correctAns2 = 2;
	    correctAns3 = 3;
	    correctAns4 = 3;
	    correctAns5 = 3;
	    question1image = getImage (getDocumentBase (), "quiz2.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz2.2.png");
	    question3image = getImage (getDocumentBase (), "quiz2.3.png");
	    question4image = getImage (getDocumentBase (), "quiz2.4.png");
	    question5image = getImage (getDocumentBase (), "quiz2.5.png");
	}
	else if (lessonCount == 3)
	{
	    lessonCount = 3; //set lesson count to 3
	    lesson (g, lesson31, lesson32, lesson33); //calls lesson method
	    correctAns1 = 3; //set correct answer for this lesson's quiz
	    correctAns2 = 2;
	    correctAns3 = 1;
	    correctAns4 = 3;
	    correctAns5 = 4;
	    question1image = getImage (getDocumentBase (), "quiz3.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz3.2.png");
	    question3image = getImage (getDocumentBase (), "quiz3.3.png");
	    question4image = getImage (getDocumentBase (), "quiz3.4.png");
	    question5image = getImage (getDocumentBase (), "quiz3.5.png");
	}
	else if (lessonCount == 4)
	{
	    lessonCount = 4; //set lesson count to 3
	    lesson (g, lesson41, lesson42, lesson43); //calls lesson method
	    correctAns1 = 4; // set correct answer for this lesson 's quiz
	    correctAns2 = 1;
	    correctAns3 = 3;
	    correctAns4 = 4;
	    correctAns5 = 2;
	    question1image = getImage (getDocumentBase (), "quiz4.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz4.2.png");
	    question3image = getImage (getDocumentBase (), "quiz4.3.png");
	    question4image = getImage (getDocumentBase (), "quiz4.4.png");
	    question5image = getImage (getDocumentBase (), "quiz4.5.png");
	}
	else if (lessonCount == 5)
	{
	    lessonCount = 5; //set lesson count to 3
	    lesson (g, lesson51, lesson52, lesson53); //calls lesson method
	    correctAns1 = 4; // set correct answer for this lesson 's quiz
	    correctAns2 = 1;
	    correctAns3 = 2;
	    correctAns4 = 1;
	    correctAns5 = 3;
	    question1image = getImage (getDocumentBase (), "quiz5.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz5.2.png");
	    question3image = getImage (getDocumentBase (), "quiz5.3.png");
	    question4image = getImage (getDocumentBase (), "quiz5.4.png");
	    question5image = getImage (getDocumentBase (), "quiz5.5.png");
	}
	else if (lessonCount == 6)
	{
	    lessonCount = 6; //set lesson count to 3
	    lesson (g, lesson61, lesson62, lesson63); //calls lesson method
	    correctAns1 = 4; // set correct answer for this lesson 's quiz
	    correctAns2 = 1;
	    correctAns3 = 3;
	    correctAns4 = 2;
	    correctAns5 = 2;
	    question1image = getImage (getDocumentBase (), "quiz6.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz6.2.png");
	    question3image = getImage (getDocumentBase (), "quiz6.3.png");
	    question4image = getImage (getDocumentBase (), "quiz6.4.png");
	    question5image = getImage (getDocumentBase (), "quiz6.5.png");
	}
	else if (lessonCount == 7)
	{
	    lessonCount = 7; //set lesson count to 3
	    lesson (g, lesson71, lesson72, lesson73); //calls lesson method
	    correctAns1 = 2; // set correct answer for this lesson 's quiz
	    correctAns2 = 1;
	    correctAns3 = 3;
	    correctAns4 = 2;
	    correctAns5 = 4;
	    question1image = getImage (getDocumentBase (), "quiz7.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz7.2.png");
	    question3image = getImage (getDocumentBase (), "quiz7.3.png");
	    question4image = getImage (getDocumentBase (), "quiz7.4.png");
	    question5image = getImage (getDocumentBase (), "quiz7.5.png");
	}
	else if (quizpressed == true)
	{
	    quiz (g); //calls quiz method
	}
    }


    //eraseButtons() sets all buttons to invisible
    public void eraseButtons ()
    {
	begin.setVisible (false);
	lesson1.setVisible (false);
	lesson2.setVisible (false);
	lesson3.setVisible (false);
	lesson4.setVisible (false);
	lesson5.setVisible (false);
	lesson6.setVisible (false);
	lesson7.setVisible (false);
	mainmenu.setVisible (false);
	lesson.setVisible (false);
	quiz.setVisible (false);
	twinkle1.setVisible (false);
	twinkle2.setVisible (false);
	cScale.setVisible (false);
	cScale1.setVisible (false);
	gScale.setVisible (false);
	dScale.setVisible (false);
	eScale.setVisible (false);
	aScale.setVisible (false);
	bScale.setVisible (false);
	fshScale.setVisible (false);
	cshScale.setVisible (false);
	fScale.setVisible (false);
	bflScale.setVisible (false);
	eflScale.setVisible (false);
	aflScale.setVisible (false);
	dflScale.setVisible (false);
	gflScale.setVisible (false);
	cflScale.setVisible (false);
	major2nd.setVisible (false);
	major3rd.setVisible (false);
	per4th.setVisible (false);
	per5th.setVisible (false);
	per5th1.setVisible (false);
	major6th.setVisible (false);
	major7th.setVisible (false);
	per8th.setVisible (false);
	per8th1.setVisible (false);
	rootPos.setVisible (false);
	rootPos1.setVisible (false);
	rootPos2.setVisible (false);
	firstInver.setVisible (false);
	secondInver.setVisible (false);
	nextPage.setVisible (false);
	prevPage.setVisible (false);
	answer1.setVisible (false);
	answer2.setVisible (false);
	answer3.setVisible (false);
	answer4.setVisible (false);
    }


    //method for welcome screen, displays welcome screen
    public void welcome (Graphics g)
    {
	if (pageChanged == true) //check if page has changed
	    eraseButtons (); //if so, clear all buttons
	pageChanged = false; //reset pageChanged to false
	begin.setVisible (true); //set the needed buttons to visible

	g.drawImage (welBG, 0, 0, this); //draw welcome screen image

    }


    public void lessonMenu (Graphics g)
    {
	if (pageChanged == true) //check if page has changed
	    eraseButtons (); //if so, clear all buttons
	pageChanged = false; //reset pageChanged to false
	lesson1.setVisible (true); //set the needed buttons to visible
	lesson2.setVisible (true);
	lesson3.setVisible (true);
	lesson4.setVisible (true);
	lesson5.setVisible (true);
	lesson6.setVisible (true);
	lesson7.setVisible (true);

	g.drawImage (lessons, 0, 0, this); //draw lesson menu image

    }


    public void lesson (Graphics g, Image page1, Image page2, Image page3)
    {
	if (pageChanged == true) //check if page has changed
	    eraseButtons (); //if so, clear all buttons
	pageChanged = false; //reset pageChanged to false
	mainmenu.setVisible (true); //set the needed buttons to visible
	quiz.setVisible (true);
	nextPage.setVisible (true);
	nextPage.setEnabled (true);
	prevPage.setVisible (true);
	prevPage.setEnabled (true);

	if (page == 1) //if user is on page 1/3 of the lesson
	{
	    g.drawImage (page1, 0, 0, this); //draw corresponding image for first page
	    prevPage.setEnabled (false); //don't let the user click previous page because there is none
	    if (lessonCount == 5) //if user is on 'scales' lesson, page 1, set 'cscale1' button to visible, as audio is needed
	    {
		cScale1.setVisible (true);
	    }
	}

	if (page == 2) //if user is on page 2/3 of the lesson
	{
	    g.drawImage (page2, 0, 0, this); //draw corresponding image for second page
	    if (lessonCount == 6) //if user is on 'intervals' lesson, page 2, set following buttons to visible, as audio is needed
	    {
		per5th1.setVisible (true);
		per8th1.setVisible (true);
	    }
	    else if (lessonCount == 7) //if user is on 'chords' lesson, page 2, set 'rootPos1' button to visible, as audio is needed
	    {
		rootPos1.setVisible (true);
	    }
	}

	if (page == 3) //if user is on page 3/3 of the lesson
	{
	    g.drawImage (page3, 0, 0, this); //draw corresponding image for second page
	    nextPage.setEnabled (false); //don't let the user go to the next page because there is none
	    if (lessonCount == 2) //if user is on 'counting' lesson, page 3, set following buttons to visible, as audio is needed
	    {
		twinkle1.setVisible (true);
		twinkle2.setVisible (true);
	    }
	    else if (lessonCount == 5) //if user is on 'scales' lesson, page 3, set following buttons to visible, as audio is needed
	    {
		cScale.setVisible (true);
		gScale.setVisible (true);
		dScale.setVisible (true);
		eScale.setVisible (true);
		aScale.setVisible (true);
		bScale.setVisible (true);
		fshScale.setVisible (true);
		cshScale.setVisible (true);
		fScale.setVisible (true);
		bflScale.setVisible (true);
		eflScale.setVisible (true);
		aflScale.setVisible (true);
		dflScale.setVisible (true);
		gflScale.setVisible (true);
		cflScale.setVisible (true);
	    }
	    else if (lessonCount == 6) //if user is on 'intervals' lesson, page 3, set following buttons to visible, as audio is needed
	    {
		major2nd.setVisible (true);
		major3rd.setVisible (true);
		per4th.setVisible (true);
		per5th.setVisible (true);
		major6th.setVisible (true);
		major7th.setVisible (true);
		per8th.setVisible (true);
	    }
	    else if (lessonCount == 7) //if user is on 'chords' lesson, page 3, set following buttons to visible, as audio is needed
	    {
		rootPos.setVisible (true);
		firstInver.setVisible (true);
		secondInver.setVisible (true);
		rootPos2.setVisible (true);
	    }

	}
    }


    //quiz method
    public void quiz (Graphics g)
    {
	if (pageChanged == true)   //check if page has changed
	    eraseButtons (); //if so, clear all buttons
	pageChanged = false; //reset pageChanged to false

	lesson.setVisible (true); //set the needed variables to visible
	mainmenu.setVisible (true);
	answer1.setVisible (true);
	answer2.setVisible (true);
	answer3.setVisible (true);
	answer4.setVisible (true);

	answer1.setEnabled (true); //re-enable all answer buttons (if user has completed quiz, they will be disabled)
	answer2.setEnabled (true);
	answer3.setEnabled (true);
	answer4.setEnabled (true);

	if (qrunning == 1) //if user is on question 1
	{
	    correctAnswer = correctAns1; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question1image, 0, 0, this); //draw the corresponding image for question
	}
	else if (qrunning == 2) //if user is on question 1
	{
	    correctAnswer = correctAns2; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question2image, 0, 0, this); //draw the corresponding image for question
	}
	else if (qrunning == 3) //if user is on question 1
	{
	    correctAnswer = correctAns3; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question3image, 0, 0, this); //draw the corresponding image for question
	}
	else if (qrunning == 4) //if user is on question 1
	{
	    correctAnswer = correctAns4; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question4image, 0, 0, this); //draw the corresponding image for question
	}
	else if (qrunning == 5) //if user is on question 1
	{
	    correctAnswer = correctAns5; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question5image, 0, 0, this); //draw the corresponding image for question
	}
	if (correct == true) //if user answered correctly
	{
	    correct = false; //reset correct to false

	    if (qrunning == 6) //if user has completed quiz
	    {
		g.drawImage (question5image, 0, 0, this); //keep showing last question
		answer1.setEnabled (false); //don't let the user click any more answer buttons
		answer2.setEnabled (false);
		answer3.setEnabled (false);
		answer4.setEnabled (false);
		JOptionPane.showMessageDialog (null, "Congrats! You have completed this lesson's quiz! Click 'go to main menu' to choose another lesson."); //display congratulatory message
		qrunning = 1; //reset question number counter
	    }
	}
    }


    //action performed method
    public void actionPerformed (ActionEvent evt)
    {
	if (evt.getSource () == begin || evt.getSource () == mainmenu) //if user has clicked 'begin' or 'main menu'
	{
	    pageChanged = true;
	    welcome = false;
	    menu = true;
	    lessonCount += 10;
	    quizpressed = false;
	    repaint ();
	}


	else if (evt.getSource () == lesson1)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 1;
	    page = 1;
	    qrunning = 1;
	    repaint ();

	}


	else if (evt.getSource () == lesson2)
	{
	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 2;
	    qrunning = 1;
	    repaint ();
	}


	else if (evt.getSource () == lesson3)
	{
	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 3;
	    qrunning = 1;
	    repaint ();
	}


	else if (evt.getSource () == lesson4)
	{

	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 4;
	    qrunning = 1;
	    repaint ();
	}


	else if (evt.getSource () == lesson5)
	{
	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 5;
	    qrunning = 1;
	    repaint ();
	}


	else if (evt.getSource () == lesson6)
	{
	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 6;
	    qrunning = 1;
	    repaint ();
	}


	else if (evt.getSource () == lesson7)
	{
	    pageChanged = true;
	    menu = false;
	    page = 1;
	    lessonCount = 7;
	    qrunning = 1;
	    repaint ();
	}


	if (evt.getSource () == prevPage)
	{
	    pageChanged = true;
	    if (page == 2)
		page = 1;
	    if (page == 3)
		page = 2;

	    repaint ();

	}


	if (evt.getSource () == nextPage)
	{

	    pageChanged = true;
	    if (page == 1)
		page = 2;
	    else if (page == 2)
		page = 3;

	    repaint ();
	}


	if (evt.getSource () == lesson)
	{
	    pageChanged = true;
	    lessonCount = lessonCount % 10;
	    quizpressed = false;
	    repaint ();
	}


	else if (evt.getSource () == quiz)
	{
	    pageChanged = true;
	    lessonCount += 10; //aka lessons = false, won't run
	    quizpressed = true;
	    repaint ();
	}

	if (evt.getSource () == answer1)
	{
	    userAnswer = 1;
	    if (userAnswer == correctAnswer)
	    {
		correct = true;
		JOptionPane.showMessageDialog (null, "Correct!");
		// repaint ();
		//next question
	    }
	    else
	    {

		correct = false;
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!");
	    }
	    repaint ();
	}


	else if (evt.getSource () == answer2)
	{
	    userAnswer = 2;
	    if (userAnswer == correctAnswer)
	    {
		correct = true;
		JOptionPane.showMessageDialog (null, "Correct!");
		// repaint ();
		//next question
	    }
	    else
	    {
		correct = false;
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}


	else if (evt.getSource () == answer3)
	{
	    userAnswer = 3;
	    if (userAnswer == correctAnswer)
	    {
		correct = true;
		JOptionPane.showMessageDialog (null, "Correct!");
		//next question
		// repaint ();
	    }
	    else
	    {
		correct = false;
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}


	else if (evt.getSource () == answer4)
	{
	    userAnswer = 4;
	    if (userAnswer == correctAnswer)
	    {
		correct = true;
		JOptionPane.showMessageDialog (null, "Correct!");
	    }
	    else
	    {
		correct = false;
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}

	if (correct == true)
	{
	    qrunning++;
	    repaint ();
	}
	if (evt.getSource () == twinkle1)
	    twinkle1a.play ();
	else if (evt.getSource () == twinkle2)
	    twinkle2a.play ();
	if (evt.getSource () == cScale || evt.getSource () == cScale1)
	    cScalea.play ();
	else if (evt.getSource () == gScale)
	    gScalea.play ();
	else if (evt.getSource () == dScale)
	    dScalea.play ();
	else if (evt.getSource () == aScale)
	    aScalea.play ();
	else if (evt.getSource () == eScale)
	    eScalea.play ();
	else if (evt.getSource () == bScale)
	    bScalea.play ();
	else if (evt.getSource () == fshScale)
	    fshScalea.play ();
	else if (evt.getSource () == cshScale)
	    cshScalea.play ();
	else if (evt.getSource () == fScale)
	    fScalea.play ();
	else if (evt.getSource () == bflScale)
	    bflScalea.play ();
	else if (evt.getSource () == eflScale)
	    eflScalea.play ();
	else if (evt.getSource () == aflScale)
	    aflScalea.play ();
	else if (evt.getSource () == dflScale)
	    dflScalea.play ();
	else if (evt.getSource () == gflScale)
	    gflScalea.play ();
	else if (evt.getSource () == cflScale)
	    cflScalea.play ();
	if (evt.getSource () == major2nd)
	    major2nda.play ();
	else if (evt.getSource () == major3rd)
	    major3rda.play ();
	else if (evt.getSource () == per4th)
	    per4tha.play ();
	else if (evt.getSource () == per5th || evt.getSource () == per5th1)
	    per5tha.play ();
	else if (evt.getSource () == major6th)
	    major6tha.play ();
	else if (evt.getSource () == major7th)
	    major7tha.play ();
	else if (evt.getSource () == per8th || evt.getSource () == per8th1)
	    per8tha.play ();
	else if (evt.getSource () == rootPos || evt.getSource () == rootPos1 || evt.getSource () == rootPos2)
	    rootPosa.play ();
	else if (evt.getSource () == firstInver)
	    firstInvera.play ();
	else if (evt.getSource () == secondInver)
	    secondInvera.play ();

    }
}



