<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

html{
    font: normal 13px sans-serif;
}

.comment-section{
    list-style: none;
    max-width: 800px;
    width: 100%;
    margin: 50px auto;
    padding: 10px;
}

.comment{
    display: flex;
    border-radius: 3px;
    margin-bottom: 45px;
    flex-wrap: wrap;
}

.comment.user-comment{
    color:  #808080;
}

.comment.author-comment{
    color:  #60686d;
    justify-content: flex-end;
}

/* User and time info */

.comment .info{
    width: 17%;
}

.comment.user-comment .info{
    text-align: right;
}

.comment.author-comment .info{
    order: 3;
}


.comment .info a{	/* User name */
    display: block;
    text-decoration: none;
    color: #656c71;
    font-weight: bold;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    padding: 10px 0 3px 0;
}

.comment .info span{	/* Time */
    font-size: 11px;
    color:  #9ca7af;
}


/* The user avatar */

.comment .avatar{
    width: 8%;
}

.comment.user-comment .avatar{
    padding: 10px 18px 0 3px;
}

.comment.author-comment .avatar{
    order: 2;
    padding: 10px 3px 0 18px;
}

.comment .avatar img{
    display: block;
    border-radius: 50%;
}

.comment.user-comment .avatar img{
    float: right;
}





/* The comment text */

.comment p{
    line-height: 1.5;
    padding: 18px 22px;
    width: 50%;
    position: relative;
    word-wrap: break-word;
}

.comment.user-comment p{
    background-color:  #f3f3f3;
}

.comment.author-comment p{
    background-color:  #e2f8ff;
    order: 1;
}

.user-comment p:after{
    content: '';
    position: absolute;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    background-color: #ffffff;
    border: 2px solid #f3f3f3;
    left: -8px;
    top: 18px;
}

.author-comment p:after{
    content: '';
    position: absolute;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    background-color: #ffffff;
    border: 2px solid #e2f8ff;
    right: -8px;
    top: 18px;
}




/* Comment form */

.write-new{
    margin: 80px auto 0;
    width: 50%;
}

.write-new textarea{
    color:  #444;
    font: inherit;

    outline: 0;
    border-radius: 3px;
    border: 1px solid #cecece;
    background-color:  #fefefe;
    box-shadow: 1px 2px 1px 0 rgba(0, 0, 0, 0.06);
    overflow: auto;

    width:100%;
    min-height: 80px;
    padding: 15px 20px;
}

.write-new img{
    border-radius: 50%;
    margin-top: 15px;
}

.write-new button{
    float:right;
    background-color:  #87bae1;
    box-shadow: 1px 2px 1px 0 rgba(0, 0, 0, 0.12);
    border-radius: 2px;
    border: 0;
    color: #ffffff;
    font-weight: bold;
    cursor: pointer;

    padding: 10px 25px;
    margin-top: 18px;
}



/* Responsive styles */

@media (max-width: 800px){
    /* Make the paragraph in the comments take up the whole width,
    forcing the avatar and user info to wrap to the next line*/
    .comment p{
        width: 100%;
    }

    /* Reverse the order of elements in the user comments,
    so that the avatar and info appear after the text. */
    .comment.user-comment .info{
        order: 3;
        text-align: left;
    }

    .comment.user-comment .avatar{
        order: 2;
    }

    .comment.user-comment p{
        order: 1;
    }


    /* Align toward the beginning of the container (to the left)
    all the elements inside the author comments. */
    .comment.author-comment{
        justify-content: flex-start;
    }


    .comment-section{
        margin-top: 10px;
    }

    .comment .info{
        width: auto;
    }

    .comment .info a{
        padding-top: 15px;
    }

    .comment.user-comment .avatar,
    .comment.author-comment .avatar{
        padding: 15px 10px 0 18px;
        width: auto;
    }

    .comment.user-comment p:after,
    .comment.author-comment p:after{
        width: 12px;
        height: 12px;
        top: initial;
        left: 28px;
        bottom: -6px;
    }

    .write-new{
        width: 100%;
    }
}



/* ------- Demo footer. Please ignore and remove ------- */

footer {
    font: normal 16px Arial, Helvetica, sans-serif;
    padding: 15px 35px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: #1B1F22;
    box-shadow: 0 -1px 1px rgba(0, 0, 0, 0.2);
    z-index: 1;
    text-align: left;
}

footer a.tz{
    font-weight: normal;
    font-size: 16px !important;
    text-decoration: none !important;
    display: block;
    margin-right: 300px;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #CCC !important;
    position: relative;
    padding-left: 140px;
}

footer a.tz:before{
    font-weight: bold;
    color: #FFF;
    content: 'tutorial';
    position: absolute;
    text-align: right;
    width: 100px;
    left: -20px;
}

footer a.tz:after{
    content: 'zine';
    position: absolute;
    font-weight: bold;
    color: #DA431C;
    left: 80px;
}


@media (max-width: 1024px) {
    footer{ display:none;}
}

</style>
</head>
<body>
	<ul class="comment-section">

		<li class="comment user-comment">

               <div class="info">
                   <a href="#">Anie Silverston</a>
                   <span>4 hours ago</span>
               </div>

               <a class="avatar" href="#">
                   <img src="resources/chat-images/avatar_user_1.jpg" width="35" alt="Profile Avatar" title="Anie Silverston" />
               </a>

               <p>Suspendisse gravida sem?</p>

		</li>

		<li class="comment author-comment">

               <div class="info">
                   <a href="#">Jack Smith</a>
                   <span>3 hours ago</span>
               </div>

               <a class="avatar" href="#">
                   <img src="resources/chat-images/avatar_author.jpg" width="35" alt="Profile Avatar" title="Jack Smith" />
               </a>

               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sem sit amet molestie portitor.</p>

		</li>

		<li class="comment user-comment">

               <div class="info">
                   <a href="#">Bradley Jones</a>
                   <span>1 hour ago</span>
               </div>

               <a class="avatar" href="#">
                   <img src="resources/chat-images/avatar_user_2.jpg" width="35" alt="Profile Avatar" title="Bradley Jones" />
               </a>

               <p>Suspendisse gravida sem sit amet molestie portitor?</p>

		</li>

           <li class="comment author-comment">

               <div class="info">
                   <a href="#">Jack Smith</a>
                   <span>1 hour ago</span>
               </div>

               <a class="avatar" href="#">
                   <img src="resources/chat-images/avatar_author.jpg" width="35" alt="Profile Avatar" title="Jack Smith" />
               </a>

               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisee gravida sem sit amet molestie porttitor.</p>

           </li>

           <li class="write-new">

               <form action="#" method="post">

                   <textarea placeholder="Write your comment here" name="comment"></textarea>

                   <div>
                       <img src="resources/chat-images/avatar_user_2.jpg" width="35" alt="Profile of Bradley Jones" title="Bradley Jones" />
                       <button type="submit">Submit</button>
                   </div>

               </form>

           </li>

	</ul>

      
</body>
</html>