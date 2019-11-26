// Name : Misgana Gebremariam
// Date: 04/23/2019
// CSC205
// Glendale Community College

import java.util.*;

public class InfiniteInt extends DLList<Integer> implements Comparable<Object>
{

	private final DLList<Integer> myList;

	//default constructor
	public InfiniteInt()
	{
		//super();
		myList = new DLList<>();
		this.addLast(0);
	}



	//Overloadded Constructor
    public InfiniteInt(String str)
    {
		  myList = new DLList<>();

        if(!str.isEmpty() || str.matches(".*\\d.*"))         // if the Input string is not empty and contains digits ---->> moves forward
        {
			int length = str.length();   					// length of the input string
			String temp = null;				 			   // Temporary String to store the substring (Max characters of 3)

			if(length < 3)				 					// Checks if the input string is length > 3
			{
				temp = str;
				this.addLast(Integer.parseInt(temp));
			}
			else if(length % 3 == 0)     					// Checks if the the string can be divided without left over parts
			{
			 	for(int i=0; i< length; i = i+3)
			 	{
					temp = str.substring(i, i+3);
					this.addLast(Integer.parseInt(temp));
			    }

			 }
			 else if(length % 3 == 1)
			 {
				 this.addFirst(Integer.parseInt(str.substring(0,1)));

			    for (int i=1; i< length; i = i+3)     // subtracted "1" to make sure i did not loose the last character
			 	{
			 		temp = str.substring(i,i+3);		  // Adds all the characters except the last remaining one character.
			 		this.addLast(Integer.parseInt(temp));
			 	}

			 }

			 else if (length % 3 == 2)
			 {

				this.addFirst(Integer.parseInt(str.substring(0,2)));

			    for (int i = 2; i < length - 2; i = i + 3)
			 	{
			 		temp = str.substring(i, i + 3);
			 		this.addLast(Integer.parseInt(temp));
			 	}
			  }
          }

        else
        {
            throw new IllegalArgumentException("Don't put trash in here");
		}
   }//end of InfiniteInt




//***********************************************************************************************************************************//



    //toString that returns the infInt as a string in format "123,456,009"
		public String toString()
		{
			if(this.head == null)

				throw new IllegalStateException("The InfiniteInt cannot be empty.");

			else
			{
				DLLNode<Integer> cursor = this.head;
		        String str = "";
		        while(cursor != null)
		        {
		            if (str.isEmpty())
		                str = str + cursor.data;
		            else if(cursor.data > 9 && cursor.data < 100)   //cursor.data.toString().length() == 2  //add 0's whenever there are less than 3 digits
		            	str = str + ",0" + cursor.data;
		            else if(cursor.data < 9)					   //cursor.data.toString().length() == 1
		            	str = str + ",00" + cursor.data;
		            else
		                str = str + "," + cursor.data;

		            	cursor = cursor.next;
		        }
		        return str;
			}
	}



/**********************************************************************************************************************************/

	//checks size to determine which is larger
		//if size is not equal it checks each individual node to see which one contains the larger data
		//if no difference in size or data is found returns 0
		public int compareTo(Object object)
		{

			try
			{
				InfiniteInt other = (InfiniteInt)object;

				if (this.size() > other.size())
					return 1;
				else if(this.size() < other.size())
					return -1;

				else //if(!this.isEmpty() && !other.isEmpty())
				{
					DLLNode<Integer> cursor = this.head;
					DLLNode<Integer> otherCursor = other.head;


					for(int i = 0; i < this.size(); i++)
					{
						if(cursor.data.equals(otherCursor.data));
						else if(cursor.data > otherCursor.data)
							return 1;
						else
							return -1;
						cursor = cursor.next;
						otherCursor = otherCursor.next;
					}

					return 0;
				 }
			   }

				catch(ClassCastException ex)
				{
					throw new ClassCastException (" Exception: error incompatible types");
				}
		   }




////******************************************************************************************************************************///


	   	//add method that adds 2 infinite ints together and returns a new infinite int
	   	//it does this by simulating addition by hand.
	   	public static InfiniteInt add(InfiniteInt int1, InfiniteInt int2)
	   	{

	   		InfiniteInt solution = new InfiniteInt();
	   		solution.removeFirst();

	   		int carry = 0;
	   		int digit;
	   		int count = 1;
	   		String intConcat = "";

	   		DLLNode<Integer> cursor1 = int1.tail;
	   		DLLNode<Integer> cursor2 = int2.tail;

	   		int num1 = cursor1.data;
	   		int num2 = cursor2.data;

	   		//same size
	   		if(int1.size() == int2.size())
	   		{
	   			while(cursor1 != null)
	   			{

	   				while(count <= 3)
	   				{
	   					//if the sum of digits/carry is less than 10, digit is sum of 2 numbers
	   					if(((num1%10)+(num2%10) + carry) < 10)
	   					{
	   						digit = ((num1%10)+(num2%10) + carry);
	   						carry = 0; //there is no carry since the sum of everything was less than 10.
	   					}
	   					else //we know the sum of the 2 and carry is greater than 9
	   					{
	   						digit = ((num1%10)+(num2%10) + carry) % 10;
	   						carry = ((num1%10)+(num2%10) + carry) / 10;
	   					}

	   					intConcat = digit + intConcat; //Concatenate a string of ints to be parsed into the node as an int
	   					count++;
	   					num1 = num1 / 10; //discard the last digits
	   					num2 = num2 / 10;

	   				}

	   				solution.addFirst(Integer.parseInt(intConcat));
	   				intConcat = "";
	   				cursor1 = cursor1.prev;
	   				cursor2 = cursor2.prev;
	   				if(cursor1 != null)
	   				{
	   					num1 = cursor1.data;
	   					num2 = cursor2.data;
	   				}
	   				count = 1;

	   			}
	   		}
	   		//case2 - inf1 is larger
	   		if(int1.size() > int2.size())
	   		{
	   			while(cursor2 != null)
	   			{

	   				while(count <= 3)
	   				{
	   					//if the sum of digits/carry is less than 10, digit is sum of 2 numbers
	   					if(((num1%10)+(num2%10) + carry) < 10)
	   					{
	   						digit = ((num1%10)+(num2%10) + carry);
	   						carry = 0; //there is no carry since the sum of everything was less than 10.
	   					}
	   					else //we know the sum of the 2 and carry is greater than 9
	   					{
	   						digit = ((num1%10)+(num2%10) + carry) % 10;
	   						carry = ((num1%10)+(num2%10) + carry) / 10;
	   					}

	   					intConcat = digit + intConcat; //Concatenate a string of ints to be parsed into the node as an int
	   					count++;
	   					num1 = num1 / 10; //discard the last digits
	   					num2 = num2 / 10;

	   				}
	   				solution.addFirst(Integer.parseInt(intConcat));
	   				intConcat = "";
	   				cursor1 = cursor1.prev;
	   				cursor2 = cursor2.prev;
	   				count = 1;

	   				if(cursor2 != null)
	   				{
	   					num1 = cursor1.data;
	   					num2 = cursor2.data;
	   				}
	   				//n2 no longer has anymore data so we will use the next loops that do not include n2
	   				num1 = cursor1.data;

	   				while(cursor1 != null && cursor2 == null)
	   				{
	   					while(count <= 3)
	   					{
	   						//if the sum of digits/carry is less than 10, digit is sum of 2 numbers
	   						if(((num1%10)+ carry) < 10)
	   						{
	   							digit = ((num1%10)+ carry);
	   							carry = 0; //there is no carry since the sum of everything was less than 10.
	   						}
	   						else //we know the sum of the 2 and carry is greater than 9
	   						{
	   							digit = ((num1%10)+ carry) % 10;
	   							carry = ((num1%10)+ carry) / 10;
	   						}

	   						intConcat = digit + intConcat; //Concatenate a string of ints to be parsed into the node as an int
	   						count++;
	   						num1 = num1 / 10; //discard the last digits
	   					}
	   					solution.addFirst(Integer.parseInt(intConcat));
	   					intConcat = "";
	   					cursor1 = cursor1.prev;
	   					count = 1; //reset count
	   					if(cursor1 != null)
	   						num1 = cursor1.data;
	   				}
	   			}
	   		}

	   		//case3 - inf1 is smaller
	   		if(int2.size() > int1.size())
	   		{
	   			while(cursor1 != null)
	   			{

	   				while(count <= 3)
	   				{
	   					//if the sum of digits/carry is less than 10, digit is sum of 2 numbers
	   					if(((num1%10)+(num2%10) + carry) < 10)
	   					{
	   						digit = ((num1%10)+(num2%10) + carry);
	   						carry = 0; //there is no carry since the sum of everything was less than 10.
	   					}
	   					else //we know the sum of the 2 and carry is greater than 9
	   					{
	   						digit = ((num1%10)+(num2%10) + carry) % 10;
	   						carry = ((num1%10)+(num2%10) + carry) / 10;
	   					}

	   					intConcat = digit + intConcat; //Concatenate a string of ints to be parsed into the node as an int
	   					count++;
	   					num1 = num1 / 10; //discard the last digits
	   					num2 = num2 / 10;

	   				}
	   				solution.addFirst(Integer.parseInt(intConcat));
	   				intConcat = "";
	   				cursor1 = cursor1.prev;
	   				cursor2 = cursor2.prev;
	   				count = 1; //reset count
	   				if(cursor1 != null)
	   				{
	   					num1 = cursor1.data;
	   					num2 = cursor2.data;
	   				}
	   				num2 = cursor2.data;
	   				//n1 no longer has anymore data so we will use the next loops that do not include n1
	   				while(cursor2 != null && cursor1 == null)
	   				{
	   					while(count <= 3)
	   					{
	   						//if the sum of digits/carry is less than 10, digit is sum of 2 numbers
	   						if(((num2%10)+ carry) < 10)
	   						{
	   							digit = ((num2%10)+ carry);
	   							carry = 0; //there is no carry since the sum of everything was less than 10.
	   						}
	   						else //we know the sum of the 2 and carry is greater than 9
	   						{
	   							digit = ((num2%10)+ carry) % 10;
	   							carry = ((num2%10)+ carry) / 10;
	   						}

	   						intConcat = digit + intConcat; //Concatenate a string of ints to be parsed into the node as an int
	   						count++;
	   						num2 = num2 / 10; //discard the last digits
	   					}
	   					solution.addFirst(Integer.parseInt(intConcat));
	   					intConcat = "";
	   					cursor2 = cursor2.prev;
	   					count = 1; //reset count
	   					if(cursor2 != null)
	   						num2 = cursor2.data;
	   				}
	   			}
	   		}
	   		if(carry >= 1)	//check if there is a final carry and add it as the first node
	   			solution.addFirst(carry);
	           return solution;
	   	}
    }








