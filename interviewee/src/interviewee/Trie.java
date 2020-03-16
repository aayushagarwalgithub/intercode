/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interviewee;
import java.util.ArrayList;

/**
 *
 * @author Aayush
 */
class Trie
{
    static class TrieNode
    {
            TrieNode[] child=new TrieNode[256];
            int cnt;
            TrieNode()
            {
                    cnt=0;
                    for(int i=0;i<256;i++)
                            child[i]=null;
            }
    };
    static TrieNode root=new TrieNode();
    static ArrayList<String> arr=new ArrayList<String >(5);
    static void dfs(TrieNode tmp,String pre)
    {
        if(tmp.cnt>=1)
                arr.add(pre);
        for(int i=0;i<256;i++)
        {
                if(tmp.child[i]!=null)
                dfs(tmp.child[i],pre+(char)(i));
        }	
    }
    static void insert(String key)
    {
        TrieNode tmp=root;
        int l=key.length();
        int i;
        for(i=0;i<l;i++)
        {
                if(tmp.child[key.charAt(i)]==null)
                        tmp.child[key.charAt(i)]=new TrieNode();
                tmp=tmp.child[key.charAt(i)];
        }
        tmp.cnt=tmp.cnt+1;
    }
    static ArrayList<String> get_list(String prefix)
    {
        arr.clear();
        TrieNode tmp=root;
        int l=prefix.length();
        int i;
        for(i=0;i<l;i++)
        {
                if(tmp.child[prefix.charAt(i)]==null)
                        tmp.child[prefix.charAt(i)]=new TrieNode();
                tmp=tmp.child[prefix.charAt(i)];	
        }
        dfs(tmp,prefix);
        return arr;
    }
    public static void main(String[] args) 
    {
            ArrayList <String > ans=new ArrayList<String >(5);
            String jkeys[]={"abcd","abcde","abcdef","abde","abdf","abdfg"};
            for(int i=0;i<jkeys.length;i++)
            {
                    insert(jkeys[i]);	
            }
            ans=get_list("abd");
            System.out.println(ans);
    }
}

