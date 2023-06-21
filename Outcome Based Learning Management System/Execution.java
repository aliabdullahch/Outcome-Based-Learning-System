import java.util.*;

import java.io.*;


abstract class BasicDML<t>
{
	public void perform_operation(ArrayList<t> array)
	{
		
	}
	public t getobj()
	{
		return null;
		
	}
}
class Add<t> extends BasicDML<t>
{
	private  t obj;
	Add(t obj)
	{
		this.obj=obj;
	}
	
	public void perform_operation(ArrayList<t> array)
	{
		array.add(obj);
	}
	public t getobj()
	{
		return obj;
	}
}
class Remove<t> extends BasicDML<t>
{
	private  t obj;
	Remove(t obj)
	{
		this.obj=obj;
	}
	public void perform_operation(ArrayList<t> array)
	{
		array.remove(obj);
	}
	public t getobj()
	{
		return obj;
	}
	
	
}
class Update<t> extends BasicDML<t>
{
	private t oldObj;
	private t newObj;
	Update(t old_obj,t new_obj)
	{
		this.oldObj=old_obj;
		this.newObj=new_obj;
		
	}
	public void perform_operation(ArrayList<t> array)
	{
		int index=-1;
		 index=array.indexOf(oldObj);
		if(index!=-1)
		{
			array.set(index, newObj);
		}
	}
}
abstract class Entity
{
	public String getId()
	{
		return null;
	}
}


// Making the User class
class User extends Entity
{
	private String userName;
	private String userId;
	public User(String n,String id)
	{
		userName=n;
		userId=id;
	}
	public void  setUserName(String n)
	{
		userName=n;
	}
	public void setUserId(String id)
	{
		userId=id;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getId()
	{
		return userId;
	}
	public void printDetails()
	{
		System.out.println("The name of the user is "+ userName+ " and \n the id of the user is "+userId);
	}
}
class AcademicOfficer extends User
{
	private ArrayList<Course> managed_courses;
	private ArrayList<Program> managed_programs;
	public AcademicOfficer(String name,String id)
	{
		super(name,id);
		managed_courses=new ArrayList<Course> ();
		managed_programs=new ArrayList<Program> ();
	}
	public void add_rem_update_courses(BasicDML obj)
	{
		obj.perform_operation(managed_courses);
	}
	public void add_rem_update_programs(BasicDML obj)
	{
		obj.perform_operation(managed_programs);
		
	}
	
}
// Making the teacher class
class Teacher extends User
{
	private ArrayList<Evaluation> evaluations_taken;
	private ArrayList<Course> teaching_courses;
	private ArrayList<CLO> totalclos;
	public Teacher (String t_name,String t_id,Course tc)
	{
		super(t_name,t_id);
		evaluations_taken= new ArrayList <Evaluation>();
		teaching_courses=new ArrayList<Course> ();
		teaching_courses.add(tc);
		totalclos=new ArrayList<CLO>();
		totalclos.addAll(tc.getclos());
	
	}
	public boolean CheckCLOhasTested(CLO obj)
	{
		int counter=0;
		for(int i=0;i<evaluations_taken.size();i++)
		{
			Evaluation eobj=evaluations_taken.get(i);
			counter+=eobj.CheckCloTested(obj);
		}
		if(counter>=2)
		{
			return true;
		}else
		{
			return false;
		}
	}
	public void add_rem_teaching_courses(BasicDML obj,Course courseobj)
	{
		obj.perform_operation(teaching_courses);
		if((obj.getClass().getName()).equals("Add"))
		{
			totalclos.addAll(courseobj.getclos());
		}
		else if((obj.getClass().getName()).equals("Remove"))
		{
			totalclos.removeAll(courseobj.getclos());
		}
		
		
		
	}
	public String  getTeachingcourseId()
	{
		return teaching_courses.get(0).getId();
	}
	public void printDetails()
	{
		super.printDetails();
		for(int i=0;i<totalclos.size();i++)
		{
			totalclos.get(i).printDetails();
		}
	}
	public void add_clos_topic(Topic tobj)
	{
		for(int i=0;i<totalclos.size();i++)
		{
			CLO temp_obj=totalclos.get(i);
			//temp_obj.printDetails();
			Add addtopic=new Add(tobj);
			temp_obj.add_rem_update_topic(addtopic);
		}
		
	}
	public CLO getcloobjbyid(String id)
	{
		CLO temp=null;
		for(int i1=0;i1<totalclos.size();i1++)
		{
			 temp=totalclos.get(i1);
			if(id.equals(temp.getId()))
			{
				break;
			}
		}
		return temp;
	}
	public Question takeinputquestion()
	{
		
		String qid,qs,cloid;int marks=0;Scanner scan=new Scanner(System.in);Scanner scan2=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter id of question");
		qid=scan2.next();
		System.out.println("Enter the statement of the question");
		qs=scan2.next();
		System.out.println("Enyer the marks of the question");
		marks=scan.nextInt();
		System.out.println("Enter the relevant clo id ");
		cloid=scan2.next();
		CLO temp=getcloobjbyid(cloid);
		Question qobj=new Question(qid,qs,temp,marks);
		return qobj;
		
	}
	public Evaluation getevaluationinstance(String id)
	{
		Evaluation temp=null;
		for(int i=0;i<evaluations_taken.size();i++)
		{
			temp=evaluations_taken.get(i);
			if(id.equals(temp.getId()))
			{
				break;
			}
		}
		return temp;
	}
	public void Manage_Evaluation()
	{
		int choice=0;Scanner scan=new Scanner(System.in);Scanner scan2=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter 1 to add a new Evaluation");
		System.out.println("Enter 2 to add a question in existing evaluation");
		System.out.println("Enter 3 to update a question in existing evaluation");
		System.out.println("Enter 4 to remove a question in existing evaluation");
		System.out.println("Enter 5 to remove an existing evaluation");
		choice=scan.nextInt();
		if(choice==1)
		{
			String type_of_eval="";Evaluation eobj=null;
			System.out.println("Enter q for quiz , a for assignment and e for exam");
			type_of_eval=scan.next();
			String evid,qid,qs,cloid;int marks=0;
			System.out.println("Enter the evaluation id ");
			evid=scan.next();
			if(type_of_eval.equals("q"))
			{
				eobj=new Quiz(evid);
			}
			else if(type_of_eval.equals("a"))
			{
				eobj=new Assignment(evid);
			}
			else if(type_of_eval.equals("e"))
			{
				eobj=new Exam (evid);
			}
			if(eobj ==null)
			{
				System.out.println("Enter the valid evaluation type");
				return;
			}
			int total_questions;
			System.out.println("Enter the total_number of questions");
			total_questions=scan.nextInt();
			for(int i=0;i<total_questions;i++)
			{
				Question qobj=takeinputquestion();
				Add qad=new Add(qobj);
				eobj.add_rem_update_Questions(qad);
				evaluations_taken.add(eobj);
			}	
		}
		else if(choice ==2)
		{
			String evid="";
			System.out.println("Enter the Evaluation id  ");
			evid=scan2.next();
			Evaluation eobj=null;
			eobj=getevaluationinstance(evid);
			Question qobj=takeinputquestion();
			Add qad=new Add(qobj);
			eobj.add_rem_update_Questions(qad);
		}
		else if(choice==3 || choice==4)
		{
			
			String evid="";
			System.out.println("Enter the Evaluation id  ");
			evid=scan2.next();
			Evaluation eobj=null;
			eobj=getevaluationinstance(evid);
			String qid="";
			System.out.println("Enter the question id you want to update or remove");
			qid=scan2.next();
			Question old_obj=null;
			ArrayList<Question> questions_list=new ArrayList<Question> ();
			questions_list.addAll(eobj.getquestions());
			
			for(int i=0;i<questions_list.size();i++)
			{
				old_obj=questions_list.get(i);
				if(qid.equals(old_obj.getId()))
				{
					break;
				}
			}
			if(old_obj==null)
			{
				System.out.println("Given question not found the specified evaluation");
				return;
			}
			// update the question
			if(choice==3)
			{
				Question new_obj=takeinputquestion();
				Update uq=new Update(old_obj,new_obj);
				eobj.add_rem_update_Questions(uq);
			}
			// remove the question
			else if(choice==4)
			{
				questions_list.remove(old_obj);
			}
			
		}
		else if(choice==5)
		{
			String evid="";
			System.out.println("Enter the Evaluation id  ");
			evid=scan2.next();
			Evaluation eobj=null;
			eobj=getevaluationinstance(evid);
			evaluations_taken.remove(eobj);
			
		}
		
	}
	public void printEvaluations()
	{
		
		for(int i=0;i<evaluations_taken.size();i++)
		{
			System.out.println("////////////////////////////////////////////////////////////////////////");
			Evaluation eobj=evaluations_taken.get(i);
			System.out.println(eobj.getId());
			System.out.println("The type of this evaluation is "+eobj.getClass().getName() );
			System.out.println("The questions of this evaluation are");
			eobj.printQuestions();
			
		}
	}
	public void WriteDatatoFile() throws IOException
	{
		FileWriter fw=new FileWriter("D:Evaluations.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(evaluations_taken.size()+"\n");
		for(int i=0;i<evaluations_taken.size();i++)
		{
			Evaluation eobj=evaluations_taken.get(i);
			bf.write(eobj.getId()+"\n");
			eobj.writedatatofile(bf);
			
		}
		bf.close();
	}
}


// Making the 
class Topic extends Entity
{
	private String topicId;
	private String topicName;
	private boolean isCovered;
	private ArrayList<CLO> relevant_clos;
	private int total_relevant_clos;
	 public Topic(String id, String name, boolean covered)
	 {
		 topicId=id;
		 topicName=name;
		 isCovered=covered;
		 total_relevant_clos=0;
		 relevant_clos=new ArrayList<CLO>();
		 
	 }
	 public void settopicName(String name)
	 {
		 topicName=name;
	 }
	 public void settopicId(String id)
	 {
		 topicId=id;
	 }
	 public void settopicCovered(boolean covered)
	 {
		 isCovered=covered;
	 }
	 public String getId()
	 {
		 return topicId;
	 }
	 public String getTopicName()
	 {
		 return topicName;
	 }
	 public boolean cover_or_no()
	 {
		 return isCovered;
	 }
	 public void printDetails()
	 {
		 System.out.println("The id of the topic is "+topicId+"\n ");
		 System.out.println("The name of the topic is "+topicName+"\n ");
		 if(isCovered)
		 {
			 System.out.println("The topic has been covered \n");
		 }else
		 {
			 System.out.println("The topic has yet not been covered \n");
		 }
		 for(int i=0;i<relevant_clos.size();i++)
		 {
			 relevant_clos.get(i).printDetails();
		 }
	 }
	 public void printMinorDetails()
	 {
		// System.out.println(topicId.length());
		 System.out.println(topicId+"\t"+topicName);
	 }

	 public void perform_add_rem(BasicDML operat)
	 {
		 operat.perform_operation( relevant_clos);
	 }
	 public void add_rem_update_clos(BasicDML obj)
	 {
		 obj.perform_operation(relevant_clos);
	 }
	 
}

class LearningOutcomes extends Entity
{
	private String outcomeId;
	private String outcomeDescription;
	public LearningOutcomes(String oid,String odesc )
	{
		outcomeId=oid;
		outcomeDescription=odesc;
	}
	public String getId()
	{
		return outcomeId;
	}

	public String getDescription()
	{
		return outcomeDescription;
	}
	public void setId(String id)
	{
		outcomeId=id;
	}

	public void setDescription(String desc)
	{
		outcomeDescription=desc;
	}
	public void printDetails()
	{
		
	}
	public void add_rem_update_topic(BasicDML obj)
	{
		
	}

}

class CLO extends LearningOutcomes
{

	private ArrayList<Topic> related_topics;
	private int total_topics;
	private ArrayList<Course> related_courses;
	private int total_rcourses;
	private PLO satisfyingPLO;
	private ArrayList<Teacher> Teachers;
	int total_teachers;
	
	
	public CLO(String Id, String desc)
	{
		super(Id,desc);
		related_topics=new<Topic> ArrayList();
		related_courses=new<Course> ArrayList();
		Teachers=new<Teacher> ArrayList();
		
		total_topics++;
		total_rcourses++;
		total_teachers++;
		
	}

	public void add_rem_update_topic(BasicDML obj)
	{
		obj.perform_operation(related_topics);
	}
	public void add_rem_update_course(BasicDML obj)
	{
		obj.perform_operation(related_courses);
	}

	public void add_rem_update_teacher(BasicDML obj)
	{
		obj.perform_operation(Teachers);
	}
	public void setSatisfyingPlo(PLO obj)
	{
		satisfyingPLO=obj;
	}
	public void printDetails()
	{
		System.out.println("The id of the CLO is "+super.getId());
		System.out.println("The description of the CLO is "+super.getDescription());
		System.out.println("The names of the courses containing this CLO are ");
		if(related_courses!=null)
		{
			for(int i=0;i<related_courses.size();i++)
			{
				System.out.println(related_courses.get(i).getcourseName());
			}
		}
		System.out.println("The names of the topics covered under this clo are");
		if(related_topics!=null)
		{
			for(int i=0;i<related_topics.size();i++)
			{
				Topic obj =related_topics.get(i);
				obj.printMinorDetails();
			}
		}
		
	}
	public void writedatatofile(BufferedWriter bf) throws IOException
	{
		
		
		for(int i=0;i<related_courses.size();i++)
		{
			bf.write(related_courses.get(i).getId()+" ");
		}
		bf.write("-1");
	}
	
}
class PLO extends LearningOutcomes
{
	Program related_Program;
	ArrayList<CLO> clos_list;
	int total_clos;
	public PLO(String id,String desc)
	{
		super(id,desc);
		clos_list=new ArrayList<CLO> ();
		total_clos=0;
	}
	public void add_rem_update_clos(BasicDML obj)
	{
		obj.perform_operation(clos_list);
	}
	public void setRelatedProgram(Program obj)
	{
		related_Program=obj;
	}
	public void getCoursesbyPLO()
	{
		//related_Program.printAllCourses();
	}
	public void printDetails()
	{
		System.out.println("The id of the PLO is "+super.getId());
		System.out.println("The description of the PLO is "+super.getDescription());
		System.out.println("The related program of the clo is "+related_Program.getName());
	}
	public void add_rem_update_topic(BasicDML obj)
	{
		
	}
	public Program getProgram()
	{
		return related_Program;
	}
	
}










// Making the course class
class Course extends Entity
{
	// making the private data members
	private String courseId;
	private String courseName;
	private String courseDescription;
	private ArrayList<User> users_list;
	private ArrayList<CLO> clos_List;
	private Program related_degree_program;
	
	private int total_clos;
	private int total_users;
	
	// making the public members
	public Course(String c_id, String c_n,String c_d)
	{
		courseId=c_id;
		courseName=c_n;
		courseDescription=c_d;
		total_clos=0;
		total_users=0;
		users_list=new<User> ArrayList();
		clos_List=new<CLO> ArrayList();
	}
	public void setcourseId(String c_id)
	{
		courseId=c_id;
	}
	public void setcourseName(String c_name)
	{
		courseName=c_name;
	}
	public void setcourseDescription(String c_d)
	{
		courseDescription=c_d;
	}
	public String  getId()
	{
		return courseId;
	}
	public String getcourseName()
	{
		return courseName;
	}
	public String getcourseDescription()
	{
		return courseDescription;
	}
	public void printDetails()
	{
		System.out.println("The id of the course is "+ courseId+ "\n");
		System.out.println("The name of the course is "+ courseName+ "\n");
		System.out.println("The description of the course is "+ courseDescription+ "\n");
		if(related_degree_program != null)
		{
			System.out.println("The degree program of this course is "+related_degree_program.getName());
		}
		
	}
	public Program getrelated_degree_program()
	{
		return related_degree_program;
	}

	public void add_rem_user(BasicDML obj)
	{
		obj.perform_operation(users_list);
	}
	
	public void add_rem_clo(BasicDML obj)
	{
		obj.perform_operation(clos_List);
	}
	

	public void setProgram(Program obj)
	{
		related_degree_program=obj;
	}
	public void printDetailedView()
	{
		this.printDetails();
		System.out.println("------------------The Clos covered under this course are--------------");
		for(int i=0;i<clos_List.size();i++)
		{
			clos_List.get(i).printDetails();
		}
	}
	public ArrayList<CLO> getclos()
	{
		return clos_List;
	}
	
	
}

// Making the program class 
class Program extends Entity
{
	// data  members
	private String programId;
	private String programName;
	private String programDescription;
	
	private ArrayList<Course> contained_courses;
	private ArrayList<PLO> contained_plos;
	private ArrayList<User> managed_by;
	
	// member functions
	public Program(String p_id, String p_name, String p_d)
	{
		programId=p_id;
		programName=p_name;
		programDescription =p_d;
		contained_courses=new <Course>ArrayList();
		contained_plos=new<PLO> ArrayList();
		managed_by=new <User>ArrayList();
		
	}
	// Making the setters
	public void setprogramId(String id)
	{
		programId=id;
	}
	public void setprogramName(String n)
	{
		programName=n;
	}
	public void setprogramDescription(String pd)
	{
		programDescription=pd;
	}
	// Making the getters
	public String getName()
	{
		return programName;
	}
	public String getId()
	{
		return programId;
	}
	public String getProgramDescription()
	{
		return programDescription;
	}
	public void add_remove_course(BasicDML obj)
	{
		obj.perform_operation(contained_courses);
	}
	public void add_rem_plos(BasicDML obj)
	{
		obj.perform_operation(contained_plos);
	}
	public void add_rem_managed_by(BasicDML obj)
	{
		obj.perform_operation(managed_by);
	}
	public void printAllplos()
	{
		for(int i=0;i<contained_plos.size();i++)
		{
			contained_plos.get(i).printDetails();
		}
	}
	public void printDetails()
	{
		System.out.println("The id of the program is "+programId+"\n");
		System.out.println("The name  of the program is "+programName+"\n");
		System.out.println("The description of the program is "+programDescription+"\n");
		System.out.println("The plos of this program are ");
		System.out.println("******************************************************");
		printAllplos();
		System.out.println("******************************************************");
		
		
	}
	
	
}


class Question extends Entity
{
	private String questionId;
	private String questionStatement;
	private int marks;
	private LearningOutcomes relevant_clo ;
	public Question(String id,String n,LearningOutcomes rclo,int m)
	{
		questionId=id;
		questionStatement=n;
		marks=m;
		relevant_clo=rclo;
	
	}
	public void setClo(CLO obj)
	{
		relevant_clo=obj;
		
	}
	public boolean chek_clo_present(CLO obj)
	{
		return relevant_clo==obj;
	}
	public void printDetails()
	{
		System.out.println("Question id: "+questionId+"  Statement:  "+questionStatement);
		System.out.println("Question Marks:  "+marks);
		System.out.println("Relevant CLO: "+relevant_clo.getDescription());
	}
	public String getId()
	{
		return questionId;
	}
	public String getStatement()
	{
		return questionStatement;
	}
}


class Evaluation extends Entity
{
	protected String eid;
	private ArrayList <Question>questions;
	public Evaluation(String id)
	{
		eid=id;
		questions=new ArrayList<Question>();
	}
	public void add_rem_update_Questions(BasicDML obj)
	{
		obj.perform_operation(questions);
	}
	public int CheckCloTested(CLO clo_obj)
	{
		int counter=0;
		for(int i=0;i<questions.size();i++)
		{
			Question question= questions.get(i);
			if(question.chek_clo_present(clo_obj))
			{
				counter++;
			}
			
		}
		return counter;
	}
	public  String getId()
	{
		return eid;
	}
	public ArrayList<Question> getquestions()
	{
		return questions;
	}
	public void printQuestions()
	{
		for(int i=0;i<questions.size();i++)
		{
			questions.get(i).printDetails();
		}
	}
	public void writedatatofile(BufferedWriter bf) throws IOException
	{
		bf.write(questions.size()+"\n");
		for(int i=0;i<questions.size();i++)
		{
			Question q=questions.get(i);
			bf.write(q.getId()+" "+q.getStatement()+"\n");
		}
	}
}
class Quiz extends Evaluation
{
	Quiz(String id)
	{
		super(id);
	}
	public  String getId()
	{
		return eid;
	}
}
class Assignment extends Evaluation
{
	Assignment(String id)
	{
		super(id);
	}
	public  String getId()
	{
		return eid;
	}
	
}
class Exam extends Evaluation
{
	Exam (String id )
	{
		super(id);
	}
	public  String getId()
	{
		return eid;
	}
}



class OBE_System implements AcademicOfficerInterface,TeacherInterface
{
	private static OBE_System obj;
	private OBE_System()
	{
		all_users=new ArrayList<User>();
		all_topics=new ArrayList<Topic>();
		all_learning_outcomes=new ArrayList<LearningOutcomes>();
		all_courses=new ArrayList<Course>();
		all_programs=new ArrayList<Program>();
		all_questions=new ArrayList<Question>();
		currently_logged_in=null;
	}
	public static OBE_System getInstance()
	{
		if(obj==null)
		{
			obj=new OBE_System();
		}
		return obj;
	}
	
	private ArrayList<User> all_users;
	private ArrayList<Topic> all_topics;
	private ArrayList<LearningOutcomes> all_learning_outcomes;
	private ArrayList<Course> all_courses;
	private ArrayList<Program> all_programs;
	private ArrayList<Question> all_questions;
	private User currently_logged_in;
	public Entity getInstanceById(String id,Entity[] array,int size)
	{

		for(int i=0;i<size;i++)
		{
			if(id.equals(array[i].getId()))
			{
				return array[i];
			}
		}
		return null;
		
	}


	public void  add_rem_user(BasicDML obj)
	{
		obj.perform_operation(all_users);
	
	}
	public void add_rem_update_alltopics(BasicDML obj)
	{
		obj.perform_operation(all_topics);
		
	}
	public void add_rem_update_all_learning_outcomes(BasicDML obj)
	{
		obj.perform_operation(all_learning_outcomes);
	}
	public void Manage_All_courses()
	{
		Scanner scan=new Scanner(System.in);int choice;int clos_size=0;
		Scanner read=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter 1 to add a new course ");
		System.out.println("Enter 2 to update an existing course ");
		System.out.println("Enter 3 to remove  an existing course ");
		choice=scan.nextInt();
		if(choice==1)
		{
			String id,name,desc,programid;
			System.out.println("Enter the id of new the course ");
			id=read.nextLine();
			System.out.println("Enter the name of the course");
			name=read.nextLine();
			System.out.println("Enter the description of the course");
			desc=read.nextLine();
			System.out.println("Enter the id of the program under which this course is taken");
			programid=read.nextLine();
			Course obj=new Course(id,name,desc);
			Entity[] programs=all_programs.toArray(new Entity [0]);
			Program p=(Program)getInstanceById(programid,programs,all_programs.size());
			obj.setProgram(p);
			all_courses.add(obj);
			Add adcourse=new Add(obj);
			AcademicOfficer acadobj=(AcademicOfficer)currently_logged_in;
			acadobj.add_rem_update_courses(adcourse);
			
		}
		else if(choice==2 ||choice==3)
		{
			String id ,mod_name,mod_desc,mod_degree;
			System.out.println("Enter the id of the existing course");
			id=scan.next();
			
			Entity[] c_array=all_courses.toArray(new Entity [0]);
			Course old_obj1=(Course)getInstanceById(id,c_array,all_courses.size());
			if(choice==2)
			{
				System.out.println("Enter the new name of the course");
				mod_name=read.nextLine();
				System.out.println("Enter the new description of the course");
				mod_desc=read.nextLine();
				System.out.println("Enter the new  degree program which you want to change with current degree program");
				mod_degree=read.nextLine();
				Course new_obj=new Course(id,mod_name,mod_desc);
				Entity [] programs=all_programs.toArray(new Entity [0]);
				Program pobj=(Program) getInstanceById(mod_degree,programs,all_programs.size());
				new_obj.setProgram(pobj);
				Update updateobj=new Update(old_obj1,new_obj);
				updateobj.perform_operation(all_courses);
				System.out.println("The course has been  updated successfully");
				AcademicOfficer acadobj=(AcademicOfficer)currently_logged_in;
				acadobj.add_rem_update_courses(updateobj);
				 
			}
			else if(choice==3)
			{
	
				all_courses.remove(old_obj1);
				Remove rcourse=new Remove(old_obj1);
				AcademicOfficer acadobj=(AcademicOfficer)currently_logged_in;
				acadobj.add_rem_update_courses(rcourse);
				System.out.println("The course having the id "+id+"has been removed successfully");
			}
			else
			{
				System.out.println("The numbered entered is invalid");
			}	
		}
	}
	public void Manage_All_CLOs()
	{
		Scanner scanno=new Scanner(System.in);
		Scanner scanstring=new Scanner(System.in).useDelimiter("\n");
		int choice=0;
		System.out.println("Enter 1 to enter a new CLO");
		System.out.println("Enter 2 to update an existing CLO");
		System.out.println("Enter 3 to remove an existing CLO");
		choice=scanno.nextInt();
		if(choice==1)
		{
			String id,desc,cid;
			System.out.println("Enter the id of the CLO");
			id=scanstring.nextLine();
			System.out.println("Enter the description of the CLO");
			desc=scanstring.nextLine();
			System.out.println("Enter the id of the course under which you want ot enter this course");
			cid=scanstring.nextLine();
			Entity [] courses=all_courses.toArray(new Entity [0]);
			Course obj=(Course)getInstanceById(cid,courses,all_courses.size());
			CLO newobj=new CLO (id,desc);
			Add addcourse=new Add(obj);
			newobj.add_rem_update_course(addcourse);
			all_learning_outcomes.add(newobj);
			System.out.println("The clo has been addedd successfully");
		}
		else if(choice==2 ||choice ==3)
		{
			String id;
			System.out.println("Enter the existing CLO id");
			id=scanstring.nextLine();
			Entity [] learningoutcomes=all_learning_outcomes.toArray(new Entity[0]);
			CLO old_obj=(CLO) getInstanceById(id,learningoutcomes,all_learning_outcomes.size());
			if(choice==2)
			{
				String description, cid;
				System.out.println("Enter the new description of the clo");
				description=scanstring.nextLine();
				CLO new_obj=new CLO(id,description);
				
				System.out.println("Enter new course id of the ClO");
				cid=scanstring.nextLine();
				Entity [] courses=all_courses.toArray(new Entity [0]);
				Course obj=(Course)getInstanceById(cid,courses,all_courses.size());
				Add addcourse=new Add(obj);
				new_obj.add_rem_update_course(addcourse);
				Update updateobj=new Update(old_obj,new_obj);
				updateobj.perform_operation(all_learning_outcomes);
				
				System.out.println("The clo has been updated successfully");
				
			}
			else if(choice==3)
			{
				all_learning_outcomes.remove(old_obj);
				System.out.println("The clo has been removed successfully");
			}
			
		}
		
		
	}
	public void LoadPrograms() throws IOException
	{
		FileReader fr=new FileReader ("D:Programs.txt");
		BufferedReader bf=new BufferedReader(fr);
		int total_programs;
		total_programs=bf.read()-48;
		bf.readLine();
		for(int i=0;i<total_programs;i++)
		{
		String id = "";int ch;
		while((ch=bf.read())!=32)
		{
			id+=(char)ch;
		}
			String name=bf.readLine();
			String description=bf.readLine();
			Program temp=new Program(id,name,description);
			all_programs.add(temp);
		}
		fr.close();
		
	}
	public void LoadCourses() throws IOException
	{
		File f=new File("D:Courses.txt");
		Scanner read=new Scanner(f);
		int total_number;
		total_number=read.nextInt();
		read.nextLine();
		for(int i=0;i<total_number;i++)
		{
			String id,name,desc,prog;
			id=read.next();
			name=read.nextLine();
			desc=read.nextLine();
			read.nextLine();
			Course temp=new Course(id,name,desc);
			all_courses.add(temp);
		}
		read.close();
		
		
	}
	public void LoadCLOs() throws IOException
	{
		File f=new File("D:CLOS.txt");
		Scanner read=new Scanner(f);
		int total=0;
		total=read.nextInt();
		read.nextLine();
		for(int i=0;i<total;i++)
		{
			String cid,cloid,desc;
			cid=read.next();
			read.nextLine();
			cloid=read.next();
			desc=read.nextLine();
			CLO temp=new CLO(cloid,desc);
			for(int j=0;j<all_courses.size();j++)
			{
				
				if(cid.equals((all_courses.get(j)).getId()))
				{
					Course temp2=all_courses.get(j);
					Add add =new Add(temp);
					temp2.add_rem_clo(add);
					Add add2=new Add(temp2);
					temp.add_rem_update_course(add2);
				}
			}
			all_learning_outcomes.add(temp);
		}
		read.close();
		
	}
	public void LoadPLOS() throws IOException
	{
		File fr=new File("D:PLOS.txt");
		
		Scanner read=new Scanner(fr);
		int total=read.nextInt();
		read.nextLine();
		for(int i=0;i<total;i++)
		{
			String ploid,desc,pid;
			pid=read.next();
			read.nextLine();
			ploid=read.next();
			read.next();
			desc=read.nextLine();
			PLO temp=new PLO(ploid,desc);
			for(int k=0;k<all_programs.size();k++)
			{
				if(pid.equals((all_programs.get(k)).getId()))
				{
					Program temp2=all_programs.get(k);
					Add add =new Add(temp);
					temp2.add_rem_plos(add);
					Add add2=new Add(temp2);
					temp.setRelatedProgram(temp2);
				}
				
				
			}
			all_learning_outcomes.add(temp);
			
		}
		read.close();
		
	}
	public void LoadTopics()throws IOException
	{
File fr=new File("D:Topics.txt");
		
		Scanner read=new Scanner(fr);
		int total=read.nextInt();
		read.nextLine();
		String tid,name,cloid;
		for(int i=0;i<total;i++)
		{
			tid=read.next();
			read.nextLine();
			name=read.nextLine();
			cloid=read.next();
			Topic tobj=new Topic (tid,name,false);
			for(int j=0;j<all_learning_outcomes.size();j++)
			{
				if((all_learning_outcomes.get(j).getId()).equals(cloid))
				{
					LearningOutcomes cloobj=all_learning_outcomes.get(j);
					Add add=new Add(tobj);
					cloobj.add_rem_update_topic(add);
					Add add2=new Add(cloobj);
					tobj.add_rem_update_clos(add2);
				}
			}
			all_topics.add(tobj);
		}
		read.close();
	}
	public void LoadQuestions() throws IOException
	{
		File fr=new File("D:Questions.txt");
		Scanner read=new Scanner(fr);
		int total=read.nextInt();
		read.nextLine();
		String qid,statement,cloid;int marks=0;
		for(int i=0;i<total;i++)
		{
			qid=read.next();
			read.nextLine();
			statement=read.nextLine();
			marks=read.nextInt();
			read.nextLine();
			cloid=read.next();
			System.out.println(cloid);
			LearningOutcomes loobj=null;

			for(int j=0;j<all_learning_outcomes.size();j++)
			{
				if((all_learning_outcomes.get(j).getId()).equals(cloid))
				{

					 loobj=all_learning_outcomes.get(j);
					
				}
			}
			Question qobj= new Question (qid,statement,loobj,marks);
			all_questions.add(qobj);
		}
		read.close();
		
	}
	public void LoadUsers() throws IOException
	{
		File fr=new File("D:Users.txt");
		Scanner read=new Scanner(fr);
		int total=read.nextInt();
		read.nextLine();
		String id,name,courseid;
		for(int i=0;i<total;i++)
		{
			id=read.next();
			//System.out.println(id);
			name=read.nextLine();
			if(id.charAt(0)=='T')
			{
				courseid=read.nextLine();
				Entity []allcourses=all_courses.toArray(new Entity [0]);
				Course obj=(Course)getInstanceById(courseid,allcourses,all_courses.size());
				Teacher newteacher=new Teacher(name,id,obj);
				//newteacher.printDetails();
				//obj.printDetails();
				all_users.add(newteacher);
				//newteacher.printDetails();
			}else
			{
				AcademicOfficer ao=new AcademicOfficer(name,id);
				all_users.add(ao);
				//ao.printDetails();
			}
			
		}	
	}
	public void LoadDataFromFiles() throws IOException
	{
		LoadPrograms();
		LoadCourses();
		LoadCLOs();
		LoadPLOS();
		LoadTopics();
		LoadQuestions();
		LoadUsers();
	}
	
	public void printPrograms()
	{
		for(int i=0;i<all_programs.size();i++)
		{
			all_programs.get(i).printDetails();
		}
	}
	public void printCourses()
	{
		for(int i=0;i<all_courses.size();i++)
		{
			System.out.println("=========================================================");
			all_courses.get(i).printDetailedView();
		}
		
	}
	public void printLOS()
	{
		for(int i=0;i<all_learning_outcomes.size();i++)
		{
			System.out.println("=========================================================");
			all_learning_outcomes.get(i).printDetails();
			
		}
	}
	void printusers()
	{
		for(int i=0;i<all_users.size();i++)
		{
			User obj=all_users.get(i);
			obj.printDetails();
		}
	}
	public User Login()
	{
		String id;Scanner scan=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter the id of the user");
		id=scan.nextLine();
		User temp=null;
		for(int i=0;i<all_users.size();i++)
		{
			temp=all_users.get(i);
			if(id.equals((temp.getId())))
			{
				break;
			}
		}
		currently_logged_in=temp;
		return currently_logged_in;
	
	}
	public void ShowAcademiCOfficerInterface()
	{
		int choice=-1;
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter 1 to manage all Courses");
		System.out.println("Enter 2 to manage all 	Clos");
		System.out.println("Enter 3 to show all Courses ");
		System.out.println("Enter 4 to print all Learning Outcomes");
		System.out.println("Enter 5 to exit");
		
		choice=scan.nextInt();
		while(choice!=5)
		{
			if(choice==1)
			{
				Manage_All_courses();
			}
			else if(choice==2)
			{
				Manage_All_CLOs();
			}
			else if(choice==3)
			{
				printCourses();
			}else if(choice==4)
			{
				printLOS();
			}
			System.out.println("Enter 1 to manage all Courses");
			System.out.println("Enter 2 to manage all 	Clos");
			System.out.println("Enter 3 to show all Courses ");
			System.out.println("Enter 4 to print all Learning Outcomes");
			System.out.println("Enter 5 to exit");
			choice=scan.nextInt();
			
		}
		
		
	}
	public void WritePLOsData() throws IOException
	{
		FileWriter fw=new FileWriter("D:PLOS.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(all_learning_outcomes.size()+"\n");
		for(int i=0;i<all_learning_outcomes.size();i++)
		{
			if(all_learning_outcomes.get(i).getClass().getName().equals("PLO"))
			{
				PLO pobj=(PLO) all_learning_outcomes.get(i);
				bf.write(pobj.getProgram().getId()+"\n");
				bf.write(pobj.getId()+" "+pobj.getDescription()+"\n");
			}
		}
		bf.close();
		
	}
	public void WriteCoursesData ()throws IOException
	{
		FileWriter fw=new FileWriter("D:Courses.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(all_courses.size()+"\n");
		for(int i=0;i<all_courses.size();i++)
		{
			Course temp=all_courses.get(i);
			bf.write(temp.getId()+" "+temp.getcourseName()+"\n");
			bf.write(temp.getcourseDescription()+"\n");
			if(temp.getrelated_degree_program()!=null)
			{
				bf.write(temp.getrelated_degree_program().getId()+"\n");
			}
			
		}
		bf.close();
	}
	public void WriteProgramsData() throws IOException
	{
		FileWriter fw=new FileWriter("D:Programs.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(all_programs.size()+"\n");
		for(int i=0;i<all_programs.size();i++)
		{
			Program temp=all_programs.get(i);
			bf.write(temp.getId()+" "+temp.getName()+"\n");
			bf.write(temp.getProgramDescription()+"\n");
		}
		bf.close();
	}
	public void WriteUsersData() throws IOException
	{
		FileWriter fw=new FileWriter("D:Users.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(all_users.size());bf.write("\n");
		for(int i=0;i<all_users.size();i++)
		{
			User temp_user=all_users.get(i);
			bf.write(temp_user.getId()+" "+temp_user.getUserName()+"\n");
			if((temp_user.getId().charAt(0))=='T')
			{
				Teacher t=(Teacher) temp_user;
				bf.write(t.getTeachingcourseId()+"\n");
			}
		}
		bf.close();
	}
	public void WriteCLOData() throws IOException
	{
		FileWriter fw=new FileWriter("D:CLOS.txt",true);
		BufferedWriter bf=new BufferedWriter(fw);
		bf.write(all_learning_outcomes.size());
		for(int i=0;i<all_learning_outcomes.size();i++)
		{
			if(all_learning_outcomes.get(i).getClass().getName().equals("CLO"))
			{
				CLO obj=(CLO) all_learning_outcomes.get(i);
				obj.writedatatofile(bf);
				bf.write(obj.getId()+" "+obj.getDescription()+"\n");
			}
		}
		bf.close();
		
	}
	public void WriteAllData() throws IOException
	{
		WriteCLOData();
		WriteCoursesData();
		WritePLOsData();
		WriteProgramsData();
		WriteUsersData();
		if(currently_logged_in.getClass().getName().equals("Teacher"))
		{
			Teacher t =(Teacher)currently_logged_in;
			t.WriteDatatoFile();
		}
		
	}
	public Topic addCLOTopic()
	{
		String id = null;String title,cloid;Scanner scanstring=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter the Topic id");
		id=scanstring.nextLine();
		Entity [] alltopics=all_topics.toArray(new Entity[0]);
		Topic tobj=null;
		tobj=(Topic)getInstanceById(id,alltopics,all_topics.size());
		if(tobj==null)
		{
			System.out.println("Topic not found");
			System.out.println("Enter the topic title");
			title=scanstring.next();
		}else
		{
			title=tobj.getTopicName();
		}
		System.out.println("Enter the clo id");
		cloid=scanstring.next();
		Entity [] allclos=all_learning_outcomes.toArray(new Entity[0]);
		CLO cloobj=null;
		cloobj=(CLO)getInstanceById(id,allclos,all_learning_outcomes.size());
		Topic newTopic=new Topic(id,title,false);
		Add cloadd=new Add(cloobj);
		newTopic.add_rem_update_clos(cloadd);
return newTopic;
		
	}
	public void showTeacherInterface()
	{
		Teacher logged_in_teacher=(Teacher)currently_logged_in;
		int choice=-1;
		Scanner scan=new Scanner(System.in);Scanner scanstring=new Scanner(System.in).useDelimiter("\n");
		System.out.println("Enter 1 to manage all evaluations");
		System.out.println("Enter 2 to add topic to existing clo");
		System.out.println("Enter 3 to check particular clo has been tested or no ");
		System.out.println("Enter 4 to show all  learning outcomes");
		System.out.println("Enter 5 to see all evaluations");
		System.out.println("Enter 6 to exit");
		
		choice=scan.nextInt();
		while(choice!=6)
		{
			if(choice==1)
			{
				logged_in_teacher.Manage_Evaluation();
			}
			else if(choice==2)
			{
				
				Topic nt=addCLOTopic();
				logged_in_teacher.add_clos_topic(nt);
			}
			else if(choice==3)
			{
				String id="";
				System.out.println("Enter the id of the clo");
				id=scanstring.next();
				Entity []closarray=all_learning_outcomes.toArray(new Entity[0]);
				CLO cloobj=(CLO)getInstanceById(id,closarray,all_learning_outcomes.size());
				if(logged_in_teacher.CheckCLOhasTested(cloobj))
				{
					System.out.println("This clo has been tested");
				}else
				{
					System.out.println("This clo has not been tested");
				}
				
			}
			else if(choice==4)
			{
				printLOS();
			}
			else if(choice==5)
			{
				if(logged_in_teacher!=null)
				{
					logged_in_teacher.printEvaluations();
				}
				
			}
			System.out.println("Enter 1 to manage all evaluations");
			System.out.println("Enter 2 to add topic to existing clo");
			System.out.println("Enter 3 to check particular clo has been tested or no");
			System.out.println("Enter 4 to print all Learning Outcomes");
			System.out.println("Enter 5 to see all evaluations");
			System.out.println("Enter 6 to exit");
			choice=scan.nextInt();
			
		}
		
		
	}

}
interface AcademicOfficerInterface
{
	public void ShowAcademiCOfficerInterface();
}
interface TeacherInterface
{
	public void showTeacherInterface();
}

public class Execution {

	public static void main(String[] args) throws IOException 
	{
		OBE_System system=OBE_System.getInstance();Scanner scan=new Scanner (System.in);
		system.LoadDataFromFiles();
		int choice=-1;	
		System.out.println("--------------------------Welcome to the Outcome Based Education Software !---------------------------");
		System.out.println("1- To see all allowed users");
		System.out.println("2- To see all programs offered");
		System.out.println("3- To login in system");
		System.out.println("4- to exit the software");
		choice=scan.nextInt();
		while(choice!=4)
		{
			if(choice==1)
			{
				system.printusers();
			}else if(choice==2)
			{
				system.printPrograms();
			}else if(choice==3)
			{
				if(system.Login().getClass().getName().equals("AcademicOfficer"))
				{
					AcademicOfficerInterface ai =system;
					ai.ShowAcademiCOfficerInterface();
				}else
				{
					TeacherInterface ti=system;
					ti.showTeacherInterface();
				}
			}
			else
			{
				return;
			}
			System.out.println("--------------------------Welcome to the Outcome Based Education Software !---------------------------");
			System.out.println("1- To see all allowed users");
			System.out.println("2- To see all programs offered");
			System.out.println("3- To login in system");
			System.out.println("4- to exit the software");
			choice=scan.nextInt();
		}
		system.WriteAllData();
		
	}

}
