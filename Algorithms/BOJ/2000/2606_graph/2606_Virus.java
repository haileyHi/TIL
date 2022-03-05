import java.io.*;
import java.util.*;

public class Main
{
    private static int N, M;
    private static int[] parent;
	public static void main(String[] args) throws IOException {
		/* 컴퓨터의 수( 100 이하 )
		* 직접 연결되어 있는 컴퓨터 쌍의 수
		* a b
		*/ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		StringTokenizer st = null;
		initParent();
		for (int i = 0; i< M; i++){
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()); // 1
		    int b = Integer.parseInt(st.nextToken()); // 6
		    if (!connected(a,b)){
		        union(a,b);
		    }
		}
		
		int cnt = 0;
		for (int i = 2; i<= N; i++){
		    if(parent[i] == 1){
		        cnt++;
		    }
		}
		System.out.println(cnt);
	}
	
	public static void initParent(){ // 부모 초기화
	    for (int i = 1; i<= N; i++){
	        parent[i] = i;
	    }
	}
	
	public static boolean connected(int a, int b) { // 두 노드 연결 여부 확인하기
	    int rootA = findParent(a);
	    int rootB = findParent(b);
	    if (rootA == rootB) return true;
	    else return false;
	}
	
	public static void union(int a, int b){ // 두 노드 연결하기
	    int rootA = findParent(a);
	    int rootB = findParent(b);
	    if (rootA > rootB) {
	        int tmp = rootA;
	        rootA = rootB;
	        rootB = tmp;
	    }
	    parent[rootB] = rootA;
	    for (int i = 1; i<= N; i++){
	        if(parent[i] == rootB){
	            parent[i] = rootA;
	        }
	    }
	}
	
	public static int findParent(int a){ // 부모 찾기
	    if (parent[a] == a) return a;
	    else return parent[a] = findParent(parent[a]);
	}
}
