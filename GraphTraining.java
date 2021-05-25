package ASD.Graph;

public class GraphTraining {
    //Misc.
    class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    class Stack{
        Node top;

        Node peek(){
            return top;
        }

        boolean isEmpty(){
            return top == null;
        }

        void push(int data){
            Node node = new Node(data);

            if(isEmpty()){
                this.top = node;
                return;
            }

            node.next = this.top;
            this.top = node;
        }

        Node pop(){
            Node node = top;
            
            if(isEmpty()){
                return null;
            }
            else if(this.top.next == null){
                this.top = null;
                return node;
            }

            this.top = this.top.next;
            return node;
        }
    }

    class Queue{
        Node front, back;

        boolean isEmpty(){
            return this.front == null;
        }

        void enqueue(int data){
            Node node = new Node(data);

            if(isEmpty()){
                this.front = node;
                this.back = node;
            } else{
                node.next = this.back;
                this.back = node;
            }
        }

        Node dequeue(){
            if(isEmpty()){
                return null;
            }
            if(this.back == this.front){
                Node node = this.back;

                this.back = null;
                this.front = null;

                return node;
            }

            Node temp = this.back;
            while(temp.next != this.front){
                temp = temp.next;
            }

            Node node = this.front;
            this.front = temp;
            this.front.next = null;
            
            return node;
        }

        void print(){
            System.out.println();
            Node temp = this.back;
            
            while(temp != null){
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
    }

    //Graph
    class Graph{
        Queue Q;
        Stack S;
        int[][] adj;

        
        Graph(int n){
            Q = new Queue();
            S = new Stack();

            adj = new int[n][n];
        }

        void addEdge(int x, int y){
            adj[x][y] = 1;
        }

        void printList(){
            for(int i = 0; i < adj.length; i++){
                System.out.printf("[%d] => ", i);
                for(int j = 0; j < adj.length; j++){
                    if(adj[i][j] == 1){
                        if(j!= adj.length - 1){
                            System.out.printf("[%d] -> ", j);
                        }
                        else{
                            System.out.printf("[%d] ", j);
                        }
                    }
                }

                System.out.println();
            }

        }

        void printMatrix(){
            for(int i = -2; i < adj.length; i++){
                for(int j = -2; j < adj.length; j++){
                    if(i == -2 && j == -2){
                        System.out.print("   ");
                    } 
                    else if(j == -1){
                        System.out.print("|");
                    }
                    else if(i == -1){
                        System.out.print("===");
                    }
                    else{
                        if(j == -2){
                            System.out.printf(" %d ", i);
                        } else if(i == -2){
                            System.out.printf(" %d ", j);
                        } else{
                            System.out.printf(" %d ", adj[i][j]);
                        }
                    }
                }
                System.out.println();
            }
        }

        /*
           | 0  1  2  3 
        ===|============
         0 | 
         1 | 
         2 |
         3 |
        */

        void BFS(int num){
            Queue Q = new Queue();
            Q.enqueue(num);

            boolean[] visited = new boolean[adj.length];
            while(!Q.isEmpty()){
                Node n = Q.dequeue();
                if(visited[n.data] == false){
                    System.out.print(n.data + " ");
                    visited[n.data] = true;
                }

                for(int i =0; i < adj.length; i++ ){
                    if(adj[n.data][i] == 1 && visited[i] == false){
                        Q.enqueue(i);
                    }
                }
                
            }
            System.out.println();
        }

        void DFS(int num){
            Stack S = new Stack();
            S.push(num);
            
            boolean[] visited = new boolean[adj.length];
            while(!S.isEmpty()){
                Node n = S.pop();
                if(visited[n.data] == false){
                    System.out.print(n.data + " ");
                    visited[n.data] = true;
                }

                for(int i = adj.length - 1; i >= 0; i--){
                    if(adj[n.data][i] == 1 && visited[i] == false){
                        S.push(i);
                    }
                }
            }

            System.out.println();
        }
    
    }
    public static void main(String[] args){
        GraphTraining gt = new GraphTraining();
        Graph G = gt.new Graph(5);

        G.addEdge(3,0);
        G.addEdge(3,1);
        G.addEdge(3,2);
        G.addEdge(1,0);
        G.addEdge(2,0);


        G.printList();
        
        System.out.println("\n");
        
        G.printMatrix();
        System.out.println();

        G.BFS(3);
        G.DFS(3);


    }
}
