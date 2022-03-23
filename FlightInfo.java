import java.util.*;
import java.io.*;
class FlightInfo
{
	String tempsrc="";
	String tempdest="";
	ArrayList<String[]> routes=new ArrayList<String[]>();
	public ArrayList<String[]> getdata() throws FileNotFoundException
	{
		Scanner myscanner = new Scanner(new File("C:\\Users\\Dell\\Downloads\\Flight_routes.csv"));
        myscanner.useDelimiter(", ");
        while (myscanner.hasNext()) 
        {
        	String[] route=myscanner.nextLine().split(",");
            routes.add(route);
        }
        myscanner.close();
        return routes;
	}
	public void showDirectFlights(ArrayList<String[]>routeInfo,String fromCity)
	{
		int exists=0;
		for(int i=0;i<routeInfo.size();i++)
		{
			String[] k=routeInfo.get(i);
			if(k[0].equals(fromCity))
			{
				exists=1;
				for(int j=0;j<k.length;j++)
					System.out.print(k[j]+"\t");
				System.out.print("\n");
			}
		}
		if(exists==0)
			System.out.print("We are sorry. At this point of time, we do not have any information on flights originating from "+fromCity);
		
	}
	public void showDirectFlights(ArrayList<String[]>routeInfo,String fromCity,String toCity)
	{
		int exists=0;
		for(int i=0;i<routeInfo.size();i++)
		{
			String[] k=routeInfo.get(i);
			if(k[0].equals(fromCity) && k[1].contentEquals(toCity))
			{
				exists=1;
				for(int j=0;j<k.length;j++)
					System.out.print(k[j]+"\t");
				System.out.print("\n");
			}
		}
		if(exists==0)
			System.out.print("We are sorry. At this point of time, we do not have any information on flights originating from "+fromCity);
		
	}
	public void showAllConnections(ArrayList<String[]> routeInfo,String srcCity,String destCity)
	{
		for(int i=0;i<routeInfo.size();i++)
		{
			String[] k=routeInfo.get(i);
			if(k[0].equals(srcCity) && k[1].equals(destCity))
			{
				System.out.println("There exists a path");
				showDirectFlights(routeInfo,tempsrc,srcCity);
				for(int j=0;j<k.length;j++)
					System.out.print(k[j]+"\t");
				System.out.print("\n");
				return;
			}
			
			else if(k[0].equals(srcCity) && !k[1].equals(destCity))
			{
				tempdest=k[1];
				showAllConnections(routeInfo,k[1],destCity);
			}	
		}
		return;
	}
	
    public static void main(String[] args) throws FileNotFoundException
    {
    	Scanner sc = new Scanner(System.in);
    	FlightInfo f = new FlightInfo();
    	System.out.print("Choose: a)Show Direct routes b)Show all connections (a/b) : ");
    	char opt=sc.next().charAt(0);
    	if(opt=='a') 
    	{
    	System.out.println("Enter your Source City : ");
    	String fromCity=sc.next();
    	f.showDirectFlights(f.getdata(),fromCity);
    	}
    	else
    	{
    		System.out.println("Enter your Source City : ");
        	String srcCity=sc.next();
        	f.tempsrc=srcCity;
        	System.out.println("Enter your Destination City : ");
        	String destCity=sc.next();
        	f.showAllConnections(f.getdata(),srcCity,destCity);
    		
    	}
    	sc.close();    	  
    }
}
