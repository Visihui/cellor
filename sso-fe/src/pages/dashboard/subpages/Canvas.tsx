import React,{ FC } from 'react';
import { useEffect,useRef } from "react";

interface DrawCanvasPropsType{
    h: number;
    w: number
}

const DrawCanvas: FC<DrawCanvasPropsType> = (props)=>{
    const cavRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const cavEl = cavRef.current;
        if(cavEl){
            const ctx = cavEl.getContext('2d');
            
            if(ctx)
            {
                //①定义样式                
          
            ctx.fillStyle='red';      
            ctx.beginPath();
            //①定义样式
            ctx.strokeStyle='green';        //颜色
            ctx.lineWidth=3;               //线宽
            ctx.lineCap='round';  //round,butt,square         //端点
            ctx.lineJoin='round';           //拐点                
            //②定义路径
            ctx.moveTo(50,50);      //起点           
            ctx.lineTo(100,100);        //拐点
            // ctx.lineTo(100,0);        //拐点           
            // ctx.lineTo(150,100);     //终点 
                   
            ctx.closePath();                //闭合
            //③绘制
            ctx.stroke();            
            ctx.fillRect(30, 30, 30, 30);
            }
            // ctx?.fillRect(10, 10, 30, 30);
           
        }        
    }, []);
    return(
        <div className="canvas-container">
            <canvas ref={cavRef} width={props.w} height={props.h} />
        </div>
    );
}

export default DrawCanvas;