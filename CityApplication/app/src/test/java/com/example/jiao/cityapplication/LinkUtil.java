package com.example.jiao.cityapplication;

import java.util.Stack;

public class LinkUtil {
    //求单链表中有效节点的个数
    //方法:获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点) /
    //@param head 链表的头节点
    //@return 返回的就是有效节点的个数 */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    //查找单链表中的倒数第 k 个结点 【新浪面试题】 //思路
    //1. 编写一个方法，接收 head 节点，同时接收一个 index
    //2. index 表示是倒数第 index 个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    // 5. 如果找到了，则返回该节点，否则返回 nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回 null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第 K 个节点 //先做一个 index 的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的 index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端 //动脑筋
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将 cur 的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将 cur 连接到新的链表上
            cur = next;//让 cur 后移
        }
        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

//    方法一：迭代
//    假设链表为 1 \rightarrow 2 \rightarrow 3 \rightarrow \varnothing1→2→3→∅，我们想要把它改成 \varnothing \leftarrow 1 \leftarrow 2 \leftarrow 3∅←1←2←3。
//
//    在遍历链表时，将当前节点的 \textit{next}next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
//    复杂度分析
//
//    时间复杂度：O(n)O(n)，其中 nn 是链表的长度。需要遍历链表一次。
//
//    空间复杂度：O(1)O(1)。

    public HeroNode reverseList(HeroNode head) {
        HeroNode prev = null;
        HeroNode curr = head;
        while (curr != null) {
            HeroNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

//    方法二：递归
//    递归版本稍微复杂一些，其关键在于反向工作。假设链表的其余部分已经被反转，现在应该如何反转它前面的部分？
//
//    假设链表为：
//
//    n_1\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_k \rightarrow n_{k+1} \rightarrow \ldots \rightarrow n_m \rightarrow \varnothing
//            n
//1
//        ​
//        →…→n
//    k−1
//            ​
//            →n
//            k
//​
//        →n
//    k+1
//            ​
//            →…→n
//            m
//​
//        →∅
//
//    若从节点 n_{k+1}n
//    k+1
//            ​
//    到 n_mn
//    m
//​
//    已经被反转，而我们正处于 n_kn
//    k
//​
//        。
//
//    n_1\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_k \rightarrow n_{k+1} \leftarrow \ldots \leftarrow n_m
//    n
//1
//        ​
//        →…→n
//    k−1
//            ​
//            →n
//            k
//​
//        →n
//    k+1
//            ​
//            ←…←n
//            m
//​
//
//
//    我们希望 n_{k+1}n
//    k+1
//            ​
//    的下一个节点指向 n_kn
//    k
//​
//        。
//
//    所以，n_k.\textit{next}.\textit{next} = n_kn
//            k
//​
//        .next.next=n
//            k
//​
//        。
//
//    需要注意的是 n_1n
//1
//        ​
//    的下一个节点必须指向 \varnothing∅。如果忽略了这一点，链表中可能会产生环。
//    复杂度分析
//
//    时间复杂度：O(n)O(n)，其中 nn 是链表的长度。需要对链表的每个节点进行反转操作。
//
//    空间复杂度：O(n)O(n)，其中 nn 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 nn 层。
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    public HeroNode reverseList2(HeroNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        HeroNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }



    //单链表的逆序打印代码:
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }

        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next; //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur 后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack 的特点是先进后出
        }
    }







    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero4);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示一把
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();
        //删除一个节点
        singleLinkedList.del(1);
        //singleLinkedList.del(4);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();
    }


}