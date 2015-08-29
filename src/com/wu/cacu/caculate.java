package com.wu.cacu;

import java.util.Stack;

/**
 * 加减乘除混合运算。
 * Created by xbuwc on 2015/8/28.
 */
public class caculate {
    /**
     * 计算表达式
     */
    static String  Expression ="9 + ( 3 - 1 ) * 3 + 10 / 2";
    /**
     * 后缀表达式
     */
    private static String suffixExpression="9 3 1 - 3 * + 10 2 / +";
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
        System.out.print(cal(suffixExpression));
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
     * 中缀表达式转后缀表达式。
     * @return
     */
    public static String InfixTosuffix(String inFixExpression){
        String[] infixArray = inFixExpression.split(" ");
        int length =infixArray.length;
/**
 * 后缀表达式动态字符串
 */
        StringBuffer suffixStr =new StringBuffer("");
        String stackpeek =null;
        for (int i = 0; i < length; i++) {
            if (isMulAndDevde(infixArray[i])){
                ExpressionStack.push(infixArray[i]);
            }else if(isPlusAndSubtract(infixArray[i])){
                if (!ExpressionStack.empty()){ //取得栈顶的数据
                    stackpeek=ExpressionStack.peek();
                }
            }else{
                suffixStr.append(infixArray[i]);
            }
        }
        return null;
    }
}
