import java.util.*;
class Node
{
  int n,s,e,w,totalTime;
  Node N,S,E,W;
  Node(int n)
  {
    n=0;e=0;w=0;s=0;totalTime=200;
  }
  void addRandomTraffic()
  {
    Random r=new Random();
    if(N==null)
      n+=r.nextInt(20);
    
    if(S==null)
      s+=r.nextInt(20);
    if(E==null)
      e+=r.nextInt(20);
    if(W==null)
      w+=r.nextInt(20);
  }
  void simulateTraffic(int time[])
  {
    n-=(time[0]/5);
    if(n<0)
      n=0;
    s-=(time[1]/5);
    if(s<0)
      s=0;
    e-=(time[2]/5);
    if(e<0)
      e=0;
    w-=(time[3]/5);
    if(w<0)
      w=0;
  }
  static int[] getIndex(float ratios[])
  {
    int index[]={0,1,2,3};
    float values[]=ratios.clone();
    for(int i=0;i<4-1;i++)
      for(int j=0;j<4-1-i;j++)
      {
        if(values[j]>values[j+1])
        {
          float tempA=values[j];
          int tempI=index[j];
          values[j]=values[j+1];
          values[j+1]=tempA;
          index[j]=index[j+1];
          index[j+1]=tempI;
        }
      }
      return index;
  }

  int[] checkTraffic()
  {
    int time[]=new int[4];
    int total=n+s+e+w;
    int newTotal=totalTime;
    	newTotal=(int)(total*1.5);
    float ratios[]={(float)((n*1.0/total))*newTotal,((float)(s*1.0/total))*newTotal,((float)(e*1.0/total))*newTotal,((float)(w*1.0/total))*newTotal};
    int indexArray[]=getIndex(ratios);
    int index;
    
  
    for(int i=0;i<4;i++)
    {
      index=indexArray[i];
      if(ratios[index]==0)
        time[index]=0;
      else if(ratios[index]<15)
      {
        time[index]=15;
        newTotal-=15;
      }
      else if(ratios[index]>newTotal)
          time[index]=newTotal;
      else
          time[index]=(int)ratios[index];
      }
      return time;
  }
  void addN(Node n)
  {
    N=n;
    n.S=this;
  }
  void addS(Node n)
  {
    S=n;
    n.N=this;
  }
  void addE(Node n)
  {
    E=n;
    n.W=this;
  }
  void addW(Node n)
  {
    W=n;
    n.E=this;
  }
  void startNode()
  {
	 addRandomTraffic();
	 for(int i=0;i<5;i++){
	  addRandomTraffic();
	  
	  displayNode();
      int time[]=checkTraffic();
      System.out.println("Time for N,S,E,W are "+time[0]+","+time[1]+","+time[2]+","+time[3]+" seconds respectively");
      simulateTraffic(time);  
      displayNodeE();
      
	 }
	 
	  }
  void displayNodeE(){
	  
	  System.out.println("\t\t"+n+"\n");
	  System.out.println("\t"+w+"\t After \t"+e+"\n");
	  System.out.println("\t\t"+s+"\n\n\n");
	  	System.out.println("More traffic added\n\n");
	  	
  } 
	  	
  
  void displayNode()
  {
	  System.out.println("\t\t"+n+"\n");
	  System.out.println("\t"+w+"\t Before \t"+e+"\n");
	  System.out.println("\t\t"+s+"\n\n\n");
	  
	  	
  }
  public static void main(String args[])
  {
      Node n1=new Node(200);
     n1.startNode();
      
    
	
      
      
      
  }
}
