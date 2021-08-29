package com.dataSctructures;

import java.io.*;
import java.util.*;

public class Graph {
    private int V; // vertices
    private LinkedList<Integer> adj[]; // adjacency list

    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void BFS(int s) {
        boolean visited[] = new boolean[V]; // marking all vertecies not visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        
        visited[s] = true;
        queue.add(s);

        while(queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");
            
            Iterator<Integer> i = adj[s].listIterator();
            while(i.hasNext()) {
                int n = i.next();
                if(!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
