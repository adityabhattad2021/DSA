import java.util.HashMap;


// Time complexity is ~O(3^n)
// Space complexity is O(n) (Because the stack size for recursion will be equal to height of our decision tree.)
// So now we can improve the efficiency of this problem using memoization.
public class MinCostOfTicketExample {

    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println("The min cost of the tickets will be: Rs "+minCostOfTicket(days, costs));
    }

    public static int minCostOfTicket(int[] days,int[] costs){
        return minCostOfTicket(days, costs, 0); 
    }


    public static int minCostOfTicket(int[] days,int[] costs,int day){
        if(day>=days.length){
            return 0;
        }


        /*
         * cost of ticket for 1 day = cost[0]
         * cost of ticket for 7 day = cost[1]
         * cost of ticket for 30 day = cost[2]
         */
        int min = Integer.MAX_VALUE;
        for(int x=0;x<costs.length;x++){
            int y;
            for(y=0;y<days.length;y++){
                if(x==0){
                    // 1 day ticket
                    if(days[y]>=days[day]+1){
                        break;
                    }
                }else if(x==1){
                    // 7 day ticket
                    if(days[y]>=days[day]+7){
                        break;
                    }
                }else if(x==2){
                    // 30 day ticket
                    if(days[y]>=days[day]+30){
                        break;
                    }
                }
            }   
            int costFromCurrentDay = minCostOfTicket(days, costs, y)+costs[x];
            if(costFromCurrentDay<min){
                min = costFromCurrentDay;
            }
        }
        return min;
    } 

}