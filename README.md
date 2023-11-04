# TO-DO_List
 
Name: - T.G.D.S. Chathuranga
Index: -  AS2021367
TaskList Class(Linked List)

Rationale and Design Decisions:
1. Linked List Data Structure: The class uses a linked list to store tasks. Linked lists are efficient for frequent insertions and deletions, which are common operations when managing a to-do list.
2. Add and Remove Methods: The "addNode" and "remove" methods allow for adding and removing tasks from the list. When removing a task, it searches for the task in the list and removes it.
3. Display Methods: The "displayList" method allows for displaying tasks based on different criteria, such as displaying all tasks or only those due today. The method also organizes tasks by their due date for better readability.
4. Sorting: The "selectionSort" method provides sorting options based on different criteria, such as title, due date, description, task added order, and priority.
5. Marking Tasks as Complete: The "markAsComplete" method marks a task as completed, removes it from the list, and stores it in a separate completed tasks array.
6. Completed Tasks: The class keeps track of completed tasks in a separate array and provides a method to display them.

Trade-offs:
1. Memory Usage: The class uses an array to store completed tasks, which has a maximum size of 100. This design choice limits the number of completed tasks that can be stored.
2. Selection Sort: The selection sort algorithm is simple to implement but may not be the most efficient sorting algorithm for larger lists. 

Task Class(Abstract data type)

Rationale and Design Decisions:
1. Task Properties: The "Task" class defines key properties of a task, including its title, description, due date, status (ongoing or completed), priority level, and a unique task ID.
2. Constructors: The class provides two constructors to allow the creation of tasks with different levels of detail (with or without a description). The first constructor accepts title, description, due date, and priority, while the second constructor sets a default description of "No Description".
3. Marking as Complete: The "markAsComplete" method allows for changing the status of a task to "completed". 
4. Status Display: The "getStatus" method returns the status of a task as a string as "ongoing" or "completed".
5. Priority Levels: The "priority" field is used to indicate the priority level of a task (e.g., high, medium, or low). The "setPriority" method allows for updating the priority level.
6. toString Method: The "toString" method generates a string representation of the task, including its Title, Description, Due Date, Priority Level, and Status.
