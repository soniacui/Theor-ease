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
The Music Theory Applet educates students about the basics of music theory. It was created for young music students (i.e. people taking beginner rudiments, grade 9 music students), or
people who just simply want to learn more about music. The Music Theory Applet contains interactive lessons and quizzes to help the user reinforce their understanding.

Author: Sonia Cui
Date: January 23, 2018

Functions:
- displays a welcome screen upon starting program
- displays a lesson menu with 7 lessons; if user clicks a lesson button, the program takes them to the corresponding lesson
- includes informative and interactive lessons to educate the user about the basics of music theory
- takes user to next or previous page of the lesson when they click the 'next' or 'prev' page
- allows user to return to the main menu or go to the quiz at any time using the 'main menu' or 'quiz' buttons
- plays the corresponding audio when an audio button is clicked
- allows user to reinforce understanding of the lessons via quizzes (5 questions per lesson)
- displays 'correct'/'incorrect' messages for feedback when the user has answered
- displays a congratulatory message when user has completed the quiz

Input: The user can click one of four 'answer' buttons at a time during the quiz for the answer they think best fits the question. Also, at various points during lessons, there will buttons
the user can click to play audio.
Output: When the user clicks an answer button, a 'correct' message will be displayed if their answer is correct, or an 'incorrect' message will be displayed if their answer is incorrect.
When the user has completed the quiz, a congratulatory message will be displayed to the user. When the user clicks audio buttons, the corresponding audio (song/scale/interval/chord) will
be played.

*/

public class MusicTheoryApplet extends Applet
    implements ActionListener
{
    final int sizex = 1130; //constants for applet dimensions
    final int sizey = 660;
    final int playx = 75; //constants for play button dimensions
    final int playy = 40;

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

    Button twinkle1 = new Button ("PLAY"); //initialize all buttons that will play audio
    Button twinkle2 = new Button ("PLAY");
    Button cScale1 = new Button ("PLAY");
    Button cScale = new Button ("PLAY");
    Button gScale = new Button ("PLAY");
    Button dScale = new Button ("PLAY");
    Button aScale = new Button ("PLAY");
    Button eScale = new Button ("PLAY");
    Button bScale = new Button ("PLAY");
    Button fshScale = new Button ("PLAY");
    Button cshScale = new Button ("PLAY");
    Button fScale = new Button ("PLAY");
    Button bflScale = new Button ("PLAY");
    Button eflScale = new Button ("PLAY");
    Button aflScale = new Button ("PLAY");
    Button dflScale = new Button ("PLAY");
    Button gflScale = new Button ("PLAY");
    Button cflScale = new Button ("PLAY");
    Button major2nd = new Button ("PLAY");
    Button major3rd = new Button ("PLAY");
    Button per4th = new Button ("PLAY");
    Button per5th1 = new Button ("PLAY");
    Button per5th = new Button ("PLAY");
    Button major6th = new Button ("PLAY");
    Button major7th = new Button ("PLAY");
    Button per8th1 = new Button ("PLAY");
    Button per8th = new Button ("PLAY");
    Button rootPos = new Button ("PLAY");
    Button rootPos1 = new Button ("PLAY");
    Button rootPos2 = new Button ("PLAY");
    Button firstInver = new Button ("PLAY");
    Button secondInver = new Button ("PLAY");

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
    private boolean flag = false;

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
	this.setSize (new Dimension (sizex, sizey)); //set default applet size

	begin.addActionListener (this);
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

	add (begin);
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

	Font myFont = new Font ("Times New Roman", Font.BOLD, 24); //create a big, bold font for answer buttons
	answer1.setFont (myFont); //buttons to new font
	answer2.setFont (myFont);
	answer3.setFont (myFont);
	answer4.setFont (myFont);

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
	//set all locations and sizes of buttons
	if (!flag) //if flag is false
	{
	    twinkle1.setSize (playx, playy);
	    twinkle2.setSize (playx, playy);
	    cScale1.setSize (playx, playy);
	    cScale.setSize (playx, playy);
	    gScale.setSize (playx, playy);
	    dScale.setSize (playx, playy);
	    aScale.setSize (playx, playy);
	    eScale.setSize (playx, playy);
	    bScale.setSize (playx, playy);
	    fshScale.setSize (playx, playy);
	    cshScale.setSize (playx, playy);
	    fScale.setSize (playx, playy);
	    bflScale.setSize (playx, playy);
	    eflScale.setSize (playx, playy);
	    aflScale.setSize (playx, playy);
	    dflScale.setSize (playx, playy);
	    gflScale.setSize (playx, playy);
	    cflScale.setSize (playx, playy);
	    major2nd.setSize (playx, playy);
	    major3rd.setSize (playx, playy);
	    per4th.setSize (playx, playy);
	    per5th1.setSize (playx, playy);
	    per5th.setSize (playx, playy);
	    major6th.setSize (playx, playy);
	    major7th.setSize (playx, playy);
	    per8th1.setSize (playx, playy);
	    per8th.setSize (playx, playy);
	    rootPos.setSize (playx, playy);
	    rootPos1.setSize (playx, playy);
	    rootPos2.setSize (playx, playy);
	    firstInver.setSize (playx, playy);
	    secondInver.setSize (playx, playy);
	    begin.setBackground (Color.orange); //make 'begin' button orange
	    this.begin.setSize (140, 75); //set button size for begin and lesson buttons
	    this.lesson1.setSize (380, 35);
	    this.lesson2.setSize (380, 35);
	    this.lesson3.setSize (380, 35);
	    this.lesson4.setSize (380, 35);
	    this.lesson5.setSize (380, 35);
	    this.lesson6.setSize (380, 35);
	    this.lesson7.setSize (380, 35);
	    this.mainmenu.setSize (100, 50);
	    this.lesson.setSize (100, 50);
	    this.nextPage.setSize (100, 50);
	    this.prevPage.setSize (100, 50);
	    this.quiz.setSize (100, 50);

	    this.lesson1.setLocation (430, 220); //set location for lesson buttons
	    this.lesson2.setLocation (430, 260);
	    this.lesson3.setLocation (430, 300);
	    this.lesson4.setLocation (430, 340);
	    this.lesson5.setLocation (430, 380);
	    this.lesson6.setLocation (430, 420);
	    this.lesson7.setLocation (430, 460);

	    this.begin.setLocation (510, 345); //set location for begin button, centre
	    this.mainmenu.setLocation (5, 5); //set location for main menu button, top left
	    this.quiz.setLocation (1000, 5); //set location for quiz button, top right
	    this.nextPage.setLocation (1000, 595); //set location for next page, bottom right
	    this.prevPage.setLocation (5, 595); //set location for previous page, bottom left
	    this.lesson.setLocation (125, 5); //set location for 'go to lesson' button, top left
	    this.answer1.setLocation (5, 250); //set location for answer buttons, aligned vertically on the left
	    this.answer2.setLocation (5, 300);
	    this.answer3.setLocation (5, 350); //300, 185 --> left 15, up 10
	    this.answer4.setLocation (5, 400);
	    this.twinkle1.setLocation (285, 176); //set location for twinkle twinkle little star audio buttons
	    this.twinkle2.setLocation (535, 495);
	    this.cScale.setLocation (120, 495); //set location for all scale-audio buttons
	    this.cScale1.setLocation (835, 535);
	    this.gScale.setLocation (560, 115);
	    this.dScale.setLocation (560, 190);
	    this.aScale.setLocation (560, 265);
	    this.eScale.setLocation (560, 340);
	    this.bScale.setLocation (560, 415);
	    this.fshScale.setLocation (560, 495);
	    this.cshScale.setLocation (560, 575);
	    this.fScale.setLocation (950, 115);
	    this.bflScale.setLocation (950, 190);
	    this.eflScale.setLocation (950, 265);
	    this.aflScale.setLocation (950, 340);
	    this.dflScale.setLocation (950, 415);
	    this.gflScale.setLocation (950, 495);
	    this.cflScale.setLocation (950, 575);
	    this.major2nd.setLocation (127, 223); //set location for interval buttons
	    this.major3rd.setLocation (127, 367);
	    this.per4th.setLocation (127, 514);
	    this.per5th.setLocation (665, 82);
	    this.per5th1.setLocation (262, 370);
	    this.major6th.setLocation (665, 232);
	    this.major7th.setLocation (665, 380);
	    this.per8th.setLocation (665, 517);
	    this.per8th1.setLocation (812, 370);
	    this.rootPos.setLocation (135, 395); //set location for chord buttons
	    this.rootPos1.setLocation (787, 430);
	    this.rootPos2.setLocation (935, 395);
	    this.firstInver.setLocation (410, 395);
	    this.secondInver.setLocation (670, 395);
	    flag = true; //set flag true
	}
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
	}
	else if (quizpressed == true)
	{
	    quiz (g); //calls quiz method
	}
    }


    //eraseButtons() sets all buttons to invisible
    public void eraseButtons ()
    {

	if (pageChanged == true) //check if page has changed
	{
	    begin.setVisible (false); //if so, reset all buttons
	    lesson1.setVisible (false); //this saves a lot of lines of code because otherwise, they would need to be set to false every method
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
	pageChanged = false; //reset pageChanged to false
    }


    //method for welcome screen, displays welcome screen
    public void welcome (Graphics g)
    {
	eraseButtons (); //reset buttons
	begin.setVisible (true); //set the needed buttons to visible
	g.drawImage (welBG, 0, 0, this); //draw welcome screen image
    }


    public void lessonMenu (Graphics g)
    {
	eraseButtons (); //reset buttons
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
	eraseButtons (); //reset buttons
	mainmenu.setVisible (true); //set the needed buttons to visible
	quiz.setVisible (true);
	nextPage.setVisible (true);
	nextPage.setEnabled (true); //reset nextPage to enabled
	prevPage.setVisible (true);
	prevPage.setEnabled (true); //reset prevPage to enabled

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
		cScale.setVisible (true); //the scale audio buttons
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
		major2nd.setVisible (true); //interval audio buttons
		major3rd.setVisible (true);
		per4th.setVisible (true);
		per5th.setVisible (true);
		major6th.setVisible (true);
		major7th.setVisible (true);
		per8th.setVisible (true);
	    }
	    else if (lessonCount == 7) //if user is on 'chords' lesson, page 3, set following buttons to visible, as audio is needed
	    {
		rootPos.setVisible (true); //chord audio buttons
		firstInver.setVisible (true);
		secondInver.setVisible (true);
		rootPos2.setVisible (true);
	    }
	}
    }


    //quiz method
    public void quiz (Graphics g)
    {
	eraseButtons (); //reset buttons

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

	else if (qrunning == 2) //if user is on question 2
	{
	    correctAnswer = correctAns2; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question2image, 0, 0, this); //draw the corresponding image for question
	}

	else if (qrunning == 3) //if user is on question 3
	{
	    correctAnswer = correctAns3; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question3image, 0, 0, this); //draw the corresponding image for question
	}

	else if (qrunning == 4) //if user is on question 4
	{
	    correctAnswer = correctAns4; //set the method's correct answer to the lesson's correct answer
	    g.drawImage (question4image, 0, 0, this); //draw the corresponding image for question
	}

	else if (qrunning == 5) //if user is on question 5
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
	    pageChanged = true; //set boolean pageChanged to true so the right buttons will be erased
	    welcome = false; //reset welcome to false
	    menu = true; //set menu to true since they're on the menu
	    quizpressed = false; //reset boolean quizpressed to false
	    repaint ();
	}
	if (evt.getSource () == lesson)  //if user has clicked 'go to lesson' button
	{
	    pageChanged = true; //reset buttons
	    lessonCount = lessonCount % 10; //get lesson number
	    quizpressed = false; //reset quizpressed boolean to false
	    repaint ();
	}
	else if (evt.getSource () == quiz)   //if user has clicked 'quiz' button
	{
	    pageChanged = true; //reset buttons
	    lessonCount += 10; //aka lessons = false, won't run
	    quizpressed = true; //set boolean 'quizpressed' to true
	    repaint ();
	}
	else if (evt.getSource () == lesson1)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 1; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz1.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz1.2.png");
	    question3image = getImage (getDocumentBase (), "quiz1.3.png");
	    question4image = getImage (getDocumentBase (), "quiz1.4.png");
	    question5image = getImage (getDocumentBase (), "quiz1.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson2)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 2; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz2.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz2.2.png");
	    question3image = getImage (getDocumentBase (), "quiz2.3.png");
	    question4image = getImage (getDocumentBase (), "quiz2.4.png");
	    question5image = getImage (getDocumentBase (), "quiz2.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson3)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 3; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz3.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz3.2.png");
	    question3image = getImage (getDocumentBase (), "quiz3.3.png");
	    question4image = getImage (getDocumentBase (), "quiz3.4.png");
	    question5image = getImage (getDocumentBase (), "quiz3.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson4)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 4; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz4.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz4.2.png");
	    question3image = getImage (getDocumentBase (), "quiz4.3.png");
	    question4image = getImage (getDocumentBase (), "quiz4.4.png");
	    question5image = getImage (getDocumentBase (), "quiz4.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson5)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 5; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz5.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz5.2.png");
	    question3image = getImage (getDocumentBase (), "quiz5.3.png");
	    question4image = getImage (getDocumentBase (), "quiz5.4.png");
	    question5image = getImage (getDocumentBase (), "quiz5.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson6)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 6; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz6.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz6.2.png");
	    question3image = getImage (getDocumentBase (), "quiz6.3.png");
	    question4image = getImage (getDocumentBase (), "quiz6.4.png");
	    question5image = getImage (getDocumentBase (), "quiz6.5.png");
	    repaint ();
	}
	else if (evt.getSource () == lesson7)
	{
	    pageChanged = true;
	    menu = false;
	    lessonCount = 7; //set lesson number to corresponding lesson
	    page = 1; //set default page to first page so user starts at the beginning of lesson each time
	    qrunning = 1; //reset quiz question number to 1
	    question1image = getImage (getDocumentBase (), "quiz7.1.png"); //import all the quiz images for this lesson
	    question2image = getImage (getDocumentBase (), "quiz7.2.png");
	    question3image = getImage (getDocumentBase (), "quiz7.3.png");
	    question4image = getImage (getDocumentBase (), "quiz7.4.png");
	    question5image = getImage (getDocumentBase (), "quiz7.5.png");
	    repaint ();
	}
	else if (evt.getSource () == prevPage)   //if user has clicked 'prev' button
	{
	    pageChanged = true; //reset buttons
	    if (page == 2) //if user is on second page, go to first page
		page = 1;
	    if (page == 3) //if user is on third page, go to second page
		page = 2;
	    repaint ();

	}
	else if (evt.getSource () == nextPage)  //if user has clicked 'next' button
	{
	    pageChanged = true; //reset buttons
	    if (page == 1) //if user is on first page, go to second page
		page = 2;
	    else if (page == 2) //if user is on second page, go to third page
		page = 3;
	    repaint ();
	}
	else if (evt.getSource () == answer1)  //if user has clicked answer button #1
	{
	    userAnswer = 1; //set their answer to 1
	    if (userAnswer == correctAnswer) //check if their answer matches the correct answer
	    {
		correct = true; //if they are correct display a 'correct' message
		JOptionPane.showMessageDialog (null, "Correct!");
	    }
	    else
	    {
		correct = false; //if they are wrong display an 'incorrect' message
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!");
	    }

	    repaint ();
	}
	else if (evt.getSource () == answer2)  //if user has clicked answer button #2
	{
	    userAnswer = 2; //set their answer to 2
	    if (userAnswer == correctAnswer) //check if their answer matches the correct answer
	    {
		correct = true; //if they are correct display a 'correct' message
		JOptionPane.showMessageDialog (null, "Correct!");
	    }
	    else
	    {
		correct = false; //if they are wrong display an 'incorrect' message
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}
	else if (evt.getSource () == answer3)  //if user has clicked answer button #3
	{
	    userAnswer = 3; //set their answer to 3
	    if (userAnswer == correctAnswer)
	    {
		correct = true; //if they are correct display a 'correct' message
		JOptionPane.showMessageDialog (null, "Correct!");
	    }
	    else
	    {
		correct = false; //if they are wrong display an 'incorrect' message
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}
	else if (evt.getSource () == answer4)   //if user has clicked answer button #4
	{
	    userAnswer = 4; //set their answer to 4
	    if (userAnswer == correctAnswer)
	    {
		correct = true; //if they are correct display a 'correct' message
		JOptionPane.showMessageDialog (null, "Correct!");
	    }
	    else
	    {
		correct = false; //if they are wrong display an 'incorrect' message
		JOptionPane.showMessageDialog (null, "Incorrect. Try again!!");
	    }
	    repaint ();
	}
	if (correct == true)   //if user is correct, go to next question
	{
	    qrunning++; //go to next question
	    repaint ();
	}
	System.gc ();

	if (evt.getSource () == twinkle1) //if user has clicked twinkle1 button
	    twinkle1a.play (); //play the twinkle1 audio clip
	else if (evt.getSource () == twinkle2)
	    twinkle2a.play ();
	else if (evt.getSource () == cScale || evt.getSource () == cScale1) //if user has clicked a scale audio button
	    cScalea.play (); //play the corresponding scale
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
	else if (evt.getSource () == major2nd) //if user has clicked an interval audio button
	    major2nda.play (); //play the corresponding interval
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
	else if (evt.getSource () == rootPos || evt.getSource () == rootPos1 || evt.getSource () == rootPos2) //if user has clicked a chord audio button
	    rootPosa.play (); //play the corresponding chord
	else if (evt.getSource () == firstInver)
	    firstInvera.play ();
	else if (evt.getSource () == secondInver)
	    secondInvera.play ();
    }
}



