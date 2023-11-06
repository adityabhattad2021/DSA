import java.util.HashMap;


// Time complexity is O((1+7+30)*n) ~ O(n)
// Space complexity is O(n) (Because the stack size will be equal to height of our decision tree.)
public class MinCostOfTicketExampleWithMemoization {

    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println("The min cost of the tickets will be: Rs "+minCostOfTicket(days, costs));
    }

    public static int minCostOfTicket(int[] days,int[] costs){
        return minCostOfTicket(days, costs,0,new HashMap<>()); 
    }


    public static int minCostOfTicket(int[] days,int[] costs,int day,HashMap<Integer,Integer> memo){
        if(day>=days.length){
            return 0;
        }

        if(memo.containsKey(day)){
            return memo.get(day);
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
            int costFromCurrentDay = minCostOfTicket(days, costs, y, memo)+costs[x];
            if(costFromCurrentDay<min){
                min = costFromCurrentDay;
            }
        }
        memo.put(day,min);
        return memo.get(day);
    } 

}