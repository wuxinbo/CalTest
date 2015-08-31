package com.wu.cacu;

import java.util.Stack;

/**
 * 加减乘除混合运算。利用后缀表达式完成计算，同时追加中缀转换为后缀。
 * Created by xbuwc on 2015/8/28.
 */
public class caculate {
    /**
     * 计算表达式
     */
    static String  Expression ="9 + ( 3 - 1 ) * 3 + 10 / 2 - 3 * 2";
    /**
     * +
     */
    private static  final String plus = "+";
    /**
     * -
     */
    private static  final String subtract = "-";
    /**
     * *
     */
    private static  final String Multiplicat = "*";
    /**
     * /
     */
    private static  final String devide = "/";

    /**
     * java 数据栈
     */
    private static Stack<String> stack =new Stack<String>();
    /**
     * 存放后缀表达式栈
     */
    private static Stack<String> ExpressionStack =new Stack<String>();

    public static void main(String [] args){
        System.out.println(Expression);
        System.out.println(InfixTosuffix(Expression));
        System.out.print(cal(InfixTosuffix(Expression)));
    }

    /**
     * 混合运算
     * @param suffixExpression
     * @return
     */
    public static  double cal(String suffixExpression){
        String [] Expressionarray =suffixExpression.split(" ");
        int length =Expressionarray.length; //字符串长度
        double firstNumber =0,twoNumber=0 ;
        for (int i=0;i<length;i++){
            if (isOpreator(Expressionarray[i])){
                twoNumber =Double.valueOf(stack.pop());
                firstNumber =Double.valueOf(stack.pop());
            }else{
                stack.push(Expressionarray[i]);
            }
            if (Expressionarray[i].equals(plus)){
                stack.push(String.valueOf(firstNumber+twoNumber));
            }else if(Expressionarray[i].equals(subtract)){
                stack.push(String.valueOf(firstNumber-twoNumber));
            }else if(Expressionarray[i].equals(Multiplicat)){
                stack.push(String.valueOf(firstNumber*twoNumber));
            }else if(Expressionarray[i].equals(devide)){
                stack.push(String.valueOf(firstNumber/twoNumber));
            }
        }
        return Double.valueOf(stack.pop());
    }

    /**
     * 是否是运算符
     * @param str
     * @return
     */
    public static boolean isOpreator(String str){
        if (str.equals(plus)||str.equals(subtract)
                ||str.equals(Multiplicat)||str.equals(devide)){
            return true;
        }
        return  false;
    }

    /**
     * 判断是否是乘法或者除法
     * @return 如果是返回true，否则返回false
     */
    public static boolean isMulAndDevde(String str){
        return str.equals(Multiplicat)||str.equals(devide);
    }

    /**
     * 是否是加法或减法。
     * @return 如果是返回true，否则返回false
     */
    public static boolean isPlusAndSubtract(String str){
        return str.equals(plus)||str.equals(subtract);
    }

    /**
     * 判断运算符的等级，
     * @param str
     * @return 如果是加减返回1,乘法或除返回2.否则返回0.返回0表示没有符合的要求。
     */
    public static int getLevelFromOpreator(String str){
        if (isPlusAndSubtract(str)){
            return 1;
        }else if (isMulAndDevde(str)){
            return 2;
        }
        return 0;
    }
    /**
     * 中缀表达式转后缀表达式。
     * @return
     */
    public static String InfixTosuffix(String inFixExpression){
        String[] infixArray = inFixExpression.split(" ");
        int length =infixArray.length;
        /*
        * 后缀表达式动态字符串
         */
        StringBuffer suffixStr =new StringBuffer("");
        String stackpeek =null;
        for (int i = 0; i < length; i++) {
            if (isMulAndDevde(infixArray[i])||infixArray[i].equals("(")){//如果当前符号是*/，执行...
                ExpressionStack.push(infixArray[i]); //将数据压到数据栈
            }else if(isPlusAndSubtract(infixArray[i])){ //如果当前符号是加号或者减号,执行。。。
                if (!ExpressionStack.empty()){ //取得栈顶的数据
                    stackpeek=ExpressionStack.peek();
                    if (getLevelFromOpreator(stackpeek)==2){//表示栈顶的运算符号是+-，优先级为最低
                        suffixStr.append(ExpressionStack.pop() +" "); //取出栈里的元素、
                        ExpressionStack.push(infixArray[i]);//将当前的元素加到表达式中。
                    }else{
                        ExpressionStack.push(infixArray[i]);
                    }
                }else{
                    ExpressionStack.push(infixArray[i]);
                }
            }else if(infixArray[i].equals(")")){ //如果当前是），需要将栈里的符号弹出，直到弹出的是（。
                while (!ExpressionStack.empty()){
                    if (ExpressionStack.peek().equals("(")){
                        ExpressionStack.pop();
                        break;
                    }else{
                        suffixStr.append(ExpressionStack.pop()+" ");
                    }
                }
            }else{
                suffixStr.append(infixArray[i]+" ");
            }
        }//由于最后的数据已经遍历完，最后将栈里面de数据全部取出。
        while (!ExpressionStack.empty()){
                suffixStr.append(ExpressionStack.pop()+" ");
        }
        return suffixStr.toString(); //返回生成好的后缀表达式。
    }
}
