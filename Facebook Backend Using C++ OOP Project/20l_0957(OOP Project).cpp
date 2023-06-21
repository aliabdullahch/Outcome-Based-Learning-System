#include<iostream>
#include<fstream>
#include<string>
#include<cstring>
using namespace std;
// This is the class of helper making the utility functions
class Helper
{
public:
	static void strcpy(char*& dest, const  char* source)
	{
		int i;
		for (i = 0; source[i] != '\0'; i++)
		{
			dest[i] = source[i];
		}
		dest[i] = '\0';
	}
	static char* GetStringFromBuffer(const char* temp)
	{
		char* string = new char[strlen(temp) + 1];
		strcpy(string, temp);
		return string;
	}
	static bool strcmp(char* str1, char* str2)
	{
		if (strlen(str1) != strlen(str2))
		{
			return false;
		}
		for (int i = 0; i < strlen(str1); i++)
		{
			if (str1[i] != str2[i])
			{
				return false;
			}
		}
		return true;
	}

};
// Following is the base class whic inherits the Class Page and the class  User
class Post;
class Baseclass
{
	char* ID;
public:
	Baseclass()
	{
		ID = 0;
	}
	void Set_ID(char* id)
	{
		ID = Helper::GetStringFromBuffer(id);
	}
	inline char* Get_ID()
	{
		return ID;
	}
	void virtual set_Homepage(Post* post)
	{

	}
	void virtual Print_Name()
	{

	}
	void virtual update_Timeline(Post* post)
	{

	}
	 virtual ~Baseclass()
	 {
		delete[] ID;
	 }

};
// Date class
class Date
{
	static Date current_date;
	int day;
	int month;
	int year;
public:
	Date()
	{
		day = 0;
		month = 0;
		year = 0;
	}
	Date(int d, int m, int y)
	{
		day = d;
		month = m;
		year = y;
	}
	static void Set_Current_Date(int d,int m,int y)
	{
		current_date.day = d;
		current_date.month = m;
		current_date.year = y;
	}
	static Date Get_Current_Date()
	{
		return current_date;
	}
	static void Print_Current_Date()
	{
		current_date.Print_Date();
	}
	void Load_Date(ifstream& read)
	{
		read >> day;
		read >> month;
		read >> year;
	}
	void Print_Date()
	{
		cout << "("<<day << "/" << month << "/" << year<<")" << endl;
	}
	int Get_Diff_from_current()
	{
		return (current_date.year - year);
	}
	// Function to give the functionality of recent 2 days
	int get_Day_Diff()
	{
		return (current_date.day - day);
	}
};
Date Date::current_date(0, 0, 0);
class User;
class Post;
class Page : public Baseclass
{
	char* title;
	User** Liked_By;
	int likedby_size;
	Post** Homepage;
	int homepage_size;
	Post** Timeline;
	int timeline_size;
	static void increased_Homepage(Post**& old_homepage, int& size)
	{
		Post** modified = new Post * [size + 1];
		for (int i = 0; i < size; i++)
		{
			modified[i] = old_homepage[i];
		}
		delete[] old_homepage;
		old_homepage = 0;
		old_homepage = modified;
		size += 1;
	}
	static void increased_Liked_By(User**& old, int& size)
	{
		User** modified = new User * [size + 1];
		for (int i = 0; i < size; i++)
		{
			modified[i] = old[i];
		}
		delete[] old;
		old = 0;
		old = modified;
		size += 1;
	}
public:
	Page()
	{
		title = 0;
		homepage_size = 0;
		Homepage = 0;
		Liked_By = 0;
		likedby_size = 0;
	}
	void Load_Pages(ifstream& take)
	{
		char temp[80] = { '\0' };
		take >> temp;
		Set_ID(temp);
		take.getline(temp, 80);
		title = Helper::GetStringFromBuffer(temp);
	}
	char* getID()
	{
		return Get_ID();
	}
	void Print();
	void set_Liked_By(User* lik_by)
	{
		increased_Liked_By(Liked_By, likedby_size);
		Liked_By[likedby_size - 1] = lik_by;
	}
	void Print_Name()
	{
		cout << title;
	}
	// Destructor which deallocates the id and title memory 
	void set_Homepage(Post* post);
	void update_Timeline(Post* post)
	{
		increased_Homepage(Timeline, timeline_size);
		Timeline[timeline_size - 1] = post;
	}
	void Print_Timeline();
	void Print_List_View()
	{
		cout <<getID() << "--";Print_Name(); cout << endl;
	}
	~Page() 
	{
		
		delete[] title;
		title = 0;
		delete[] Liked_By;
		delete[] Homepage;
		for (int i = 0; i < timeline_size; i++)
		{
			delete Timeline[i];
		}
		delete[] Timeline;
	}
	
};
class User : public Baseclass
{
	char* first_name;
	char* last_name;
	User** friend_list;
	int no_of_friends;
	Page** liked_pages;
	int no_of_likedpages;
	Post ** Homepage;
	int homepage_size;
	Post** Timeline;
	int timeline_size;
	// Actuall this function increase the size of the list of liked_pages one by one
	static void increased_pagelist(Page**& old_pages, int& size)
	{
		Page** modified = new Page * [size + 1];
		for (int i = 0; i < size; i++)
		{
			modified[i] = old_pages[i];
		}
		delete[] old_pages;
		old_pages = 0;
		old_pages = modified;
		size += 1;
	}
	// This function increases the size of the list of Friend_list one by one
	static void increased_friendlist(User**& old_friendlist, int& size)
	{
		User** modified = new User * [size + 1];
		for (int i = 0; i < size; i++)
		{
			modified[i] = old_friendlist[i];
		}
		delete[] old_friendlist;
		old_friendlist = 0;
		old_friendlist = modified;
		size += 1;
	}
	static void increased_Homepage(Post **& old_homepage, int& size)
	{
		Post** modified = new Post * [size + 1];
		for (int i = 0; i < size; i++)
		{
			modified[i] = old_homepage[i];
		}
		delete[] old_homepage;
		old_homepage = 0;
		old_homepage = modified;
		size += 1;
	}

public:
	// Default constructor which do nthing initailizes with zero
	User()
	{

		first_name = 0;
		last_name = 0;
		friend_list = 0;
		no_of_friends = 0;
		liked_pages = 0;
		no_of_likedpages = 0;
		Homepage = 0;
		homepage_size = 0;
	}
	void Load_User_FromFile(ifstream& read)
	{
		char temp[30] = { '\0' };
		read >> temp;
		Set_ID(temp);
		read >> temp;
		first_name = Helper::GetStringFromBuffer(temp);
		read >> temp;
		last_name = Helper::GetStringFromBuffer(temp);
	}
	void set_Liked_pages(Page* liked_page)
	{
		increased_pagelist(liked_pages, no_of_likedpages);
		liked_pages[no_of_likedpages - 1] = liked_page;
	}
	void set_friendlist(User* user_friend)
	{
		increased_friendlist(friend_list, no_of_friends);
		friend_list[no_of_friends - 1] = user_friend;
	}
	char* getID()
	{
		return Get_ID();
	}
	void Print_Homepage();
	void Print_id_name()
	{
			cout <<getID() << "--";Print_Name(); cout << endl;
	}
	
	void Print_Name()
	{
		cout << first_name << " " << last_name;
	}
	// Prints the friend list of the a particular user
	void view_friendlist()
	{
		cout << "------------------------------------------------------------------------------------------------------------\n";
		Print_Name(); cout << " -Friendlist" << endl;
		for (int i = 0; i < no_of_friends; i++)
		{
			friend_list[i]->Print_id_name();
		}
		cout << "---------------------------------------------------------------------------------------------------------------\n";
	}
	void view_likedpages()
	{
		cout << "------------------------------------------------------------------------------------------------------------\n";
		Print_Name(); cout << " -Liked Pages" << endl;
		for (int j = 0; j < no_of_likedpages; j++)
		{
			liked_pages[j]->Print_List_View();
		}
		cout << "-------------------------------------------------------------------------------------------------------------\n" << endl;
	}
	// Destructor which deletes the *(this) id,first name,last name,arra of pointers(friendlist and the liked_pages)

	
	void set_Homepage(Post* post);
	void update_homepage(Post* post)
	{
		increased_Homepage(Homepage, homepage_size);
		Homepage[homepage_size - 1] = post;
	}
	void update_Timeline(Post* post)
	{
		increased_Homepage(Timeline, timeline_size);
		Timeline[timeline_size - 1] = post;
	}
	void Print_Timeline();
	void Share_Memory(Post* o_p, char* n_comment);
	~User()
	{

		delete[] first_name;
		delete[] last_name;
		delete[] friend_list;
		delete[] liked_pages;
		delete[] Homepage;
		for (int i = 0; i < timeline_size; i++)
		{
			delete Timeline[i];
		}
		delete[] Timeline;
	}
};
void User::set_Homepage(Post * post)
{
	for (int i = 0; i < no_of_friends; i++)
	{
		friend_list[i]->update_homepage(post);
	}
}
void Page::set_Homepage(Post* post)
{
	for (int i = 0; i < likedby_size; i++)
	{
		Liked_By[i]->update_homepage(post);
	}
}
void Page::Print()
{
	cout << Get_ID() << "\t\t" << title << "\n";
	for (int i = 0; i < likedby_size; i++)
	{
		cout << Liked_By[i]->Get_ID() << "\t";
	}
	cout << endl;

}
//  Class of Activity 
class Activity
{
	int type;
	char* name_of_activity;
public:
	Activity()
	{
		type = 0;
		name_of_activity = 0;
	}
	void Load_Activity(ifstream &read)
	{
		read >> type;
		char temp[50] = { '\0' };
		read.getline(temp, 50);
		name_of_activity = Helper::GetStringFromBuffer(temp);
	}
	// function to print the type and value of activity
	void Print_Activity()
	{
		switch (type)
		{
		case 1:
		{
			cout << " is feeling " << name_of_activity << endl;
			break;
		}
		case 2:
		{
			cout << " is thinking about " << name_of_activity << endl;
			break;
		}
		case 3:
		{
			cout << " is making " << name_of_activity << endl;
			break;
		}
		case 4:
		{
			cout << "is celebrating " << name_of_activity << endl;
			break;
		}
		default:
		{
			cout << "shared";
			break;
		}

		}

	}
	~Activity()
	{
		delete[] name_of_activity;
	}

};
class Comment
{
	char* cid;
	char* comment_text;
	Baseclass* Commented_By;

public:
	Comment()
	{
		comment_text = 0;
		Commented_By = 0;
	}
	Comment(Baseclass* commentedby, char* com_text)
	{
		Commented_By = commentedby;
		comment_text = Helper::GetStringFromBuffer(com_text);
	}
	void Set_Comment_id(char* temp)
	{
		cid = Helper::GetStringFromBuffer(temp);
	}
	void Add_Comment_Text(char* temp)
	{
		comment_text = Helper::GetStringFromBuffer(temp);
	}
	void Set_Commented_by(Baseclass* coment_by)
	{
		Commented_By = coment_by;
	}
	char * get_comment_text()
	{
		return comment_text;
	}
	char* GetID()
	{
		return cid;
	}
	void Print_Comment()
	{
		cout << "                      ";
		Commented_By->Print_Name();
		cout << " wrote : " << comment_text << endl;
	}
	~Comment()
	{
		delete[] cid;
		delete[] comment_text;
	}
	
};
class Post
{
	char* pid;
	int type;
	Date date_of_creation;
	char* text;
	Activity* activity;
	Baseclass* shared_by;
	Baseclass** liked_by;
	Comment** Comments;
	int com_size;
	int likedby_size;
public:
	Post()
	{
		pid = 0;
		text = nullptr;
		liked_by = new Baseclass * [10];
		Comments = new Comment * [10];
		com_size = 0;
		likedby_size = 0;
	}
	void Load_Post(ifstream& read)
	{
		type = 0; char temp[500] = { '\0' };
		read >> type; read.ignore();
		read.getline(temp,500);
		pid = Helper::GetStringFromBuffer(temp);
		date_of_creation.Load_Date(read);
		read.ignore();
		read.getline(temp, 500);
		text = Helper::GetStringFromBuffer(temp);
		if (type == 1)
		{
			activity = 0;
		}
		else
		{
			activity = new Activity;
			activity->Load_Activity(read);
		}

	}
	void Set_Shared_By(Baseclass* ptr)
	{
		shared_by = ptr;
	}
	Baseclass* Get_Shared_By()
	{
		return shared_by;
	}
	void Set_Liked_By(Baseclass* ptr)
	{
		
		liked_by[likedby_size] = ptr;
		likedby_size++;
	}
	inline char* Get_ID()
	{
		return pid;
	}
	void Print_Post()
	{
		cout << "--->";
		shared_by->Print_Name(); cout << " ";
		if (activity)
		{
			activity->Print_Activity();
		}
		else
		{
			cout << " shared ";
		}
		cout <<"                "<<text << endl;
		for (int i = 0; i < com_size; i++)
		{
			Comments[i]->Print_Comment();
		}
		cout << "\n";
	}
	void Add_Comment(Comment * comment)
	{
		Comments[com_size] = comment;
		com_size++;
	}
	void Print_Likedby()
	{
		for (int i = 0; i <likedby_size; i++)
		{
			cout << liked_by[i]->Get_ID() << "--";
			liked_by[i]->Print_Name();
			cout << "\n";
		}
		cout << endl;
	}
	// Printing style of the sharing a memory
	void Print_in_Memory()
	{
		cout << "--->";
		shared_by->Print_Name(); cout << " ";
		if (activity)
		{
			activity->Print_Activity();
		}
		else
		{
			cout << " shared ";
		}
		cout << "                " << text;
		cout << "......"; date_of_creation.Print_Date(); cout << endl;

	}
	void virtual  Print_Timeline_Post()
	{
		cout << "--->";
		shared_by->Print_Name(); cout << " ";
		if (activity)
		{
			activity->Print_Activity();
		}
		else
		{
			cout << " shared ";
		}
		cout << "                " << text;
		cout << "......"; date_of_creation.Print_Date(); cout << endl;
		for (int i = 0; i < com_size; i++)
		{
			Comments[i]->Print_Comment();
		}
		cout << "\n";
	}
	void set_Post_text(char *c_text)
	{
		text = Helper::GetStringFromBuffer(c_text);
	}
	void set_Post_Date(Date d)
	{
		date_of_creation = d;
	}
	Date Get_Date()
	{
		return date_of_creation;
	}
	char* get_Post_Text()
	{
		return text;
	}
	Date get_Post_Date()
	{
		return date_of_creation;
	}
	virtual ~Post()
	{
		delete[] pid;
		delete[] text;
		if (activity)
		{
			delete activity;;
		}
		delete[] liked_by;
		for (int i = 0; i < com_size; i++)
		{
			delete Comments[i];
		}
		delete[] Comments;
	}

};
class Memory:public Post
{
	Post* Original_Post;
public:
	Memory(Post * prev_post,char * newcomment,Date mem_date)
	{
		Original_Post = prev_post;
		set_Post_text(newcomment);
		set_Post_Date(mem_date);
	}
	void Print_Timeline_Post()
	{
		cout << "---> ~~~"; Original_Post->Get_Shared_By()->Print_Name(); cout << " shared a Memory ~~~       " << endl;
		cout << get_Post_Text();
		//Date::Print_Current_Date();
		Date date_of_memory=get_Post_Date();
		date_of_memory.Print_Date();
		cout << "                  On this day " << Original_Post->Get_Date().Get_Diff_from_current() << " years ago " << endl;
		Original_Post->Print_in_Memory();
	}
};
// Following functions are the member functions of User and the page Dep_readed before but they need functionality before which is dep_readed after in the post class
void User::Print_Homepage()
{
	for (int i = 0; i < homepage_size; i++)
	{
		Date date_of_post = Homepage[i]->Get_Date();
		if (date_of_post.get_Day_Diff() < 2)
		{
			Homepage[i]->Print_Post();
		}
	
	}
}
void User::Print_Timeline()
{
	for (int i = 0; i < timeline_size; i++)
	{
		Timeline[i]->Print_Timeline_Post();
	}
}
void Page::Print_Timeline()
{

	for (int i = 0; i < timeline_size; i++)
	{
		Timeline[i]->Print_Timeline_Post();
	}
}
void User::Share_Memory(Post* o_p, char* n_comment)
{/*
	Post * memory=new Memory (o_p, n_comment,);
	update_Timeline(memory);*/
}
class Facebook
{
	User** controlled_users;
	int user_size;
	Page** controlled_pages;
	int page_size;
	Post** stored_posts;
	int post_size;
	Comment** stored_comments;
	int com_size;
	// Function to serach a particular page and return its address
	static Page* search_pages(Page** controlled_pages, char* str2, int size)
	{
		for (int i = 0; i < size; i++)
		{
			if (Helper::strcmp(controlled_pages[i]->getID(), str2))
			{
				return controlled_pages[i];
			}
		}
		return 0;

	}
	// Function to search a particular user and return its address
	static User* search_users(User** controlled_users, char* str2, int size)
	{
		for (int i = 0; i < size; i++)
		{
			if (Helper::strcmp(controlled_users[i]->getID(), str2))
			{
				return controlled_users[i];
			}
		}
		return 0;

	}
	 static Baseclass* search_by_id(User** controlled_users, Page** controlled_pages,char* str2, int size1,int size2)
	 {
		for (int i = 0; i < size1; i++)
		{
			if (Helper::strcmp(controlled_users[i]->getID(), str2))
			{
				return controlled_users[i];
			}
		}
		
	    for (int i = 0; i < size2; i++)
		{
			if (Helper::strcmp(controlled_pages[i]->getID(), str2))
			{
				return controlled_pages[i];
			}
		}
		return 0;
		
	 }
	 // Function to search in the posts
	 static Post* search_in_posts(Post** posts, int size,char * temp)
	 {
		 for (int i = 0; i < size; i++)
		 {
			 if (Helper::strcmp(posts[i]->Get_ID(), temp))
			 {
				 return posts[i];
			 }
		 }
		 return 0;
	 }
public:
	Facebook()
	{
		controlled_users = 0;
		user_size = 0;
		page_size = 0;
		controlled_pages = 0;
	}
	// Function which loads the user id , first name ,last name friendlist and liked pages
	void Load_Users(ifstream& read)
	{
		int count = 0;
		read >> count;
		read.ignore();
		user_size = count;
		controlled_users = new User * [count];
		int max_friends = 10;
		char*** temp_friend_list = new char** [count];
		int* nooffriends = new int[count];
		for (int i = 0; i < count; i++)
		{
			controlled_users[i] = new User;
			// User Id ,first and last name are read from the member function
			controlled_users[i]->Load_User_FromFile(read);
			temp_friend_list[i] = new char* [max_friends];
			char temp[30] = { '\0' };
			read >> temp; int j = 0;
			char delimeter[5] = "-1";
			// Temmporary reading the ids  of each user in the double pointer pointed to by the the tripple pointer
			while (!(Helper::strcmp(temp, delimeter)))
			{
				temp_friend_list[i][j] = Helper::GetStringFromBuffer(temp);
				j++;
				read >> temp;
			}
			nooffriends[i] = j;
			read >> temp;
			// reading the page and linking the page in the liked pages of a particular user
			while (!(Helper::strcmp(temp, delimeter)))
			{
				Page* page_link = search_pages(controlled_pages, temp, page_size);
				if (page_link)
				{
					page_link->set_Liked_By(controlled_users[i]);
					controlled_users[i]->set_Liked_pages(page_link);
				}
				read >> temp;
			}

		}
		// Linking the temporary array of friendlists of each user in the friend list of each user
		for (int i = 0; i < user_size; i++)
		{
			for (int j = 0; j < nooffriends[i]; j++)
			{
				User* Userlink = search_users(controlled_users, temp_friend_list[i][j], user_size);
				if (Userlink)
				{
					controlled_users[i]->set_friendlist(Userlink);
				}

			}

		}
		delete[] nooffriends;
		nooffriends = 0;

	}
	// Function to load the pages which further calls the Load_Pages function on ith page
	void Load_Pages(ifstream& take)
	{
		int count = 0;
		take >> count;
		page_size = count;
		take.ignore();
		controlled_pages = new Page * [count];
		for (int i = 0; i < count; i++)
		{
			controlled_pages[i] = new Page;
			controlled_pages[i]->Load_Pages(take);
		}
	}
	// Function to Load the comments from the fike named as comments.txt
	void Load_Comments(ifstream& fread)
	{
		fread >> com_size;
		stored_comments = new Comment * [com_size];
		for (int i = 0; i < com_size; i++)
		{
			char temp[80] = { '\0' };
			stored_comments[i] = new Comment;
			fread >> temp;
			stored_comments[i]->Set_Comment_id(temp);
			fread >> temp;
			Post* on_this_post = search_in_posts(stored_posts, post_size, temp);
			if (on_this_post)
			{
				on_this_post->Add_Comment(stored_comments[i]);
			}
			fread >> temp;
			Baseclass* commentedby = search_by_id(controlled_users, controlled_pages, temp, user_size, page_size);
			stored_comments[i]->Set_Commented_by(commentedby);
			fread.getline(temp, 80);
			stored_comments[i]->Add_Comment_Text(temp);
		}

	}
	// Function to load the Posts from tthe file named as posts.txt
	void Load_Posts(ifstream& p_read)
	{
		p_read >> post_size;
		stored_posts = new Post * [post_size]; int* arr = new int[20];
		for (int i = 0; i < post_size; i++)
		{
			char temp[50] = { '\0' };
			stored_posts[i] = new Post;
			stored_posts[i]->Load_Post(p_read);
			p_read.getline(temp, 50);
			Baseclass* sharedby = search_by_id(controlled_users, controlled_pages, temp, user_size, page_size);
			if (sharedby)
			{
				stored_posts[i]->Set_Shared_By(sharedby);
			}
			char delimeter[5] = "-1"; int index = 0;
			p_read >> temp;
			while (!(Helper::strcmp(temp, delimeter)))
			{
				Baseclass *liked_by= search_by_id(controlled_users, controlled_pages, temp, user_size, page_size);
				if (liked_by)
				{
					stored_posts[i]->Set_Liked_By(liked_by);
					index++;
				}
				
				p_read >> temp;
			}
			arr[i] = index;

		}
		delete[] arr;
		arr = 0;
	
	}

	// Function which calls two functions and loads all the data of the facebook
	void Load_Data()
	{
		ifstream pa_read, u_read, pos_read, c_read;
	    pa_read.open("D:Pages.txt", ios::in);
		if (pa_read.is_open())
		{
			cout << " Pages has been read successfully" << endl;
		}
		Load_Pages(pa_read); pa_read.close();
		u_read.open("D:Users.txt", ios::in);
		if (u_read.is_open())
		{
			cout << "Users  has been read successfully" << endl;
		}
		Load_Users(u_read); u_read.close();
		pos_read.open("D:posts.txt", ios::in);
		if (pos_read.is_open())
		{
			cout << "Posts ahs been read successfully \n";
		}
		Load_Posts(pos_read); pos_read.close();
		c_read.open("D:comments.txt", ios::in);
		if (c_read.is_open())
		{
			cout << "File named comments has been read Successfully" << endl;
		}
		Load_Comments(c_read); c_read.close();
	}
	// Function to print all the pages stored in the facebook database(Not mentioned in the document)
	void Print_Pages()
	{
		for (int i = 0; i < page_size; i++)
		{
			controlled_pages[i]->Print();
			cout << endl;
		}
	}
	// Function which sets the current user and calls the view friendlist and view liked pages 
	void Run()
	{
		char log_in_user_id[50] = "u7";
		cout << "Command:                Set current user as " << log_in_user_id << "\n";
		User* currently_logged_in = Facebook::search_users(controlled_users, log_in_user_id, user_size);
		currently_logged_in->Print_Name(); cout << " successfully set as current user \n" << endl;
		cout << "Command:                View Friend List \n" << endl;
		currently_logged_in->view_friendlist();
		cout << "Comand:                  View Liked Pages \n" << endl;
		currently_logged_in->view_likedpages();
		currently_logged_in->Print_Homepage();
		cout << "-----------------------------------------------------------------------------------------" << endl;
		cout << "Command: View Timeline" << endl;
		cout << "-----------------------------------------------------------------------------------------" << endl;
		currently_logged_in->Print_Timeline();
		cout << "-----------------------------------------------------------------------------------------" << endl;
		cout << "Command:View Liked List (post5) " << endl;
		cout << "-----------------------------------------------------------------------------------------" << endl;
		char post_id1[50] = "post5";
		Post* Clicked_Post = search_in_posts(stored_posts, post_size, post_id1);
		if (Clicked_Post)
		{
			Clicked_Post->Print_Likedby();
		}
		cout << "-----------------------------------------------------------------------------------------" << endl;
		cout << "Command: LikePost(post5)" << endl;
		Clicked_Post->Set_Liked_By(currently_logged_in);
		cout << "Command: View Liked List (post5)" << endl;
		cout << "-----------------------------------------------------------------------------------------" << endl;
		if (Clicked_Post)
		{
			Clicked_Post->Print_Likedby();
		}
		cout << "Command: Post Comment(post 8,Thanks for the Wishes) " << endl;
		char comment[30] = " Thanks for the Wishes";
		char post_id2[30] = "post8";
		Comment* newcomment = new Comment(currently_logged_in, comment);
		Clicked_Post = search_in_posts(stored_posts, post_size, post_id2);
		if (Clicked_Post)
		{
			Clicked_Post->Add_Comment(newcomment);
			Clicked_Post->Print_Timeline_Post();
		}
		cout << "-----------------------------------------------------------------------------------------" << endl;
		cout << "Command: ShareMemory(post10, Never thought I will be specialist in this field )" << endl;
		char postid3[30] = "post10";
		char comment2[80] = "Never thought I will be specialist in this field......";
		Clicked_Post = search_in_posts(stored_posts, post_size, postid3);
		Memory* mem = new Memory(Clicked_Post, comment2,Date::Get_Current_Date());
		currently_logged_in->update_Timeline(mem);
		currently_logged_in->Print_Timeline();
		cout << "-----------------------------------------------------------------------------------------" << endl;
		cout << "Command: View Page (p1)" << endl;
		char pagename[10] = "p1";
		Page* Clicked_Page = search_pages(controlled_pages, pagename, page_size);
		if (Clicked_Page)
		{
			Clicked_Page->Print_Timeline();
		}
		
	}
	// This function loads the homepage of the User's freinds or the the users who liked a particular page controlled by virtual function in the baseclass
	void Load_Homepage()
	{

		for (int i = 0; i < post_size; i++)
		{
			Baseclass* temp = stored_posts[i]->Get_Shared_By();
			temp->set_Homepage(stored_posts[i]);
		}
	}
	// Thsi function loads thge timeline of  a user or a page controlled by virtual function
	void Load_Timeline()
	{
		for (int i = 0; i < post_size; i++)
		{
			Baseclass* temp = stored_posts[i]->Get_Shared_By();
			temp->update_Timeline(stored_posts[i]);
		}
	}
	// Destructor of the facebook which implicitly calls the destructor of each and every class used
	~Facebook()
	{
		for (int i = 0; i < user_size; i++)
		{
			delete controlled_users[i];
		}
		for (int j = 0; j < page_size; j++)
		{
			delete controlled_pages[j];
		}
		// following deallocates the array of pointers created on the heap
		delete[] controlled_users;
		delete[] controlled_pages;
		delete[] stored_posts;
		delete[] stored_comments;
	}

	
};
int main()
{
		Facebook fb;
		Date::Set_Current_Date(15, 11, 2017);
		fb.Load_Data();
		fb.Load_Homepage();
		fb.Load_Timeline();
		fb.Run();
}
