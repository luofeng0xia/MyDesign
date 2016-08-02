2016/7/30 19:58:06 
##Design包学习
在开发之前导入design包  
com.android.support:design
###TabLayout
选项卡的标签布局，配合ViewPager使用，首先在XML布局文件中引入

  	<android.support.design.widget.TabLayout
                android:id="@+id/tab"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                ...
                >
    </android.support.design.widget.TabLayout>
TabLayout专有属性要在独立命名空间下，在布局文件的根布局下引入

	xmlns:app="http://schemas.android.com/apk/res-auto"
这里署名为`app`，则在下面属性也要用`app`，下面是一些属性，标签文字的大小无法直接用size属性指定，只能用`tabTextAppearance`属性直接指定style样式

	app:tabBackground="@color/colorPrimary" //标签栏的背景
    app:tabTextColor="@android:color/white" //未选中标签文字的颜色
    app:tabIndicatorColor="@android:color/white" //滑块的颜色
    app:tabSelectedTextColor="@color/colorAccent" //选中标签文字的颜色
    app:tabMode="scrollable" //标签的模式，如果标签比较多时会跟随页面滚动
java代码中与ViewPager联动

    ViewPager pager = (ViewPager) findViewById(R.id.pager);
    TabLayout tab = (TabLayout) findViewById(R.id.tab);
    MyAdapter adapter=new MyAdapter(getSupportFragmentManager(),list);
    pager.setAdapter(adapter);
    tab.setupWithViewPager(pager); //在pager内容设置好后，设置与TabLayout的联动
TabLayout标签上的文字来自于ViewPager，需要重写adapter的getPageTitle()方法

	public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
###DrawerLayout
包括内容和抽屉(NavigationView)，可以选择覆盖toolbar或者跟toolbar联动，覆盖只需要把toolbar布局作为DrawerLayout内容的一部分，不覆盖则DrawerLayout应该在toolbar的下方，可以拥有抽屉的一个汉堡动画

* **Toolbar**  
  Toolbar用来代替ActionBar可以更自由的摆放 
   
    1.backgruound属性用来指定toolbar的背景
theme属性用来指定整个主题，`@style/ThemeOverlay.AppCompat.Dark.ActionBar"`这个主题前景色是白色

	    <android.support.v7.widget.Toolbar
	        android:id="@+id/toolbar"
	        android:background="@color/colorPrimary"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
	    </android.support.v7.widget.Toolbar>
    2.在设置toolbar之前，应该先把程序原来的actionBar去掉，换NoActionBar的主题，例如`Theme.AppCompat.Light.NoActionBar`

    3.在`setContentView()`方法之后，找到toolbar然后设置，`setDisplayHomeAsUpEnabled()`方法可以添加一个箭头图标，在有父Activity时能够返回。

		Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
* **NavigationView**  
扮演抽屉导航中的抽屉，必须设置layout_gravity属性，代表向哪个方向滑动，特有属性也是在`app`命名空间下

	    <android.support.v4.widget.DrawerLayout
	        android:id="@+id/drawer"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	        <LinearLayout
				android:layout_width="match_parent"
	        	android:layout_height="match_parent">
	            ...
	        </LinearLayout>
	        <android.support.design.widget.NavigationView
	            android:id="@+id/nav"
	            android:layout_gravity="start"
	            app:menu="@menu/navigation"
	            app:headerLayout="@layout/header"
	            app:itemTextColor="@color/item_bg_select"
	            app:itemIconTint="@color/item_bg_select"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent">
	        </android.support.design.widget.NavigationView>
	    </android.support.v4.widget.DrawerLayout>
1.设置抽屉里的菜单  
新建menu文件，group标签内的item是一组单选菜单，有选中和未选中之分，item-menu-item承接出来的菜单有子菜单的效果，会有一条横线隔开
  
	    <?xml version="1.0" encoding="utf-8"?>
		<menu xmlns:android="http://schemas.android.com/apk/res/android">
		    <group android:checkableBehavior="single">
		        <item
		            android:id="@+id/single_1"
		            android:icon="@mipmap/ic_launcher"
		            android:title="单选1" />
		        <item
		            android:id="@+id/single_2"
		            android:icon="@mipmap/ic_launcher"
		            android:title="单选2" />
		    </group>
		    <item android:title="子菜单">
		        <menu>
		            <item
		                android:id="@+id/nav_item_1"
		                android:icon="@mipmap/ic_launcher"
		                android:title="菜单1" />
		            <item
		                android:id="@+id/nav_item_2"
		                android:icon="@mipmap/ic_launcher"
		                android:title="菜单2" />
		        </menu>
		    </item>
		</menu>
 在NavigationView中配置`app:menu`属性，即上面的menu文件  

	2.设置抽屉里的header，显示用户的头像和用户名   
新建header布局文件，写死header的高度，设置背景色，添加imageview和textview承载用户头像和名字

		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:layout_width="match_parent"
		    android:layout_height="200dp"
		    android:gravity="center"
		    android:background="@drawable/header_bg"
		    android:orientation="vertical">
		    <ImageView
		        android:src="@mipmap/ic_launcher"
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content" />
		    <TextView
		        android:layout_marginTop="16dp"
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content"
		        android:text="用户名"/>
		</LinearLayout>
在NavigationView中配置`app:headerLayout`属性，即上面的layout文件

    3.设置抽屉的监听事件和与toolbar的联动  
寻找到drawerlayout后利用ActionBarDrawerToggle对象控制toolbar上的图标与抽屉的联动

		drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); //同步状态 开或关
给NavigationView添加监听事件，并重写监听回调

	 	nav.setNavigationItemSelectedListener(this);
		--------------------分割线-------------------
		public boolean onNavigationItemSelected(MenuItem item) {
		     switch (item.getItemId()) { 
		         case R.id.nav_item_4: //事件处理
		              finish();
		              break;
		         }
		     drawer.closeDrawer(GravityCompat.START); //每次点击后关闭抽屉
		     return true;
		}
###SnackBar
这个控件和Toast相似，出现位置是屏幕下方，可以给它添加动作，比如撤销，在CoordinatorLayout中可以配合侧滑删除

	Snackbar.make(view,"snackbar",Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                text.setVisibility(View.VISIBLE);
	                text.animate().alpha(1);
	            }
	        }).show();
###CoordinatorLayout
继承自FrameLayout，在没有设置Behavior之前和帧布局无差，通过给它里面的控件设置Behavior可以达到不同的效果。

* **自定义Behavior**  
   一系列的行为监听，当发生什么事情，应该做什么事情。给控件设置Behavior的方式有三种。  
   1.创建自定义Behavior类继承自系统的Behavior，重写onStartNestedScroll()方法和onNestedPreScroll()方法
   
		public class MyBehavior extends CoordinatorLayout.Behavior {
		    @Override
		    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
		        return true;
		    }
		    @Override
		    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
		        super.onNestedPreScroll(coordinatorLayout, 
		        // TODU:
			}
		}
   然后通过控件的LayoutParams设置

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) text.getLayoutParams();
        layoutParams.setBehavior(new MyBehavior());
2.重写Behavior两个参数的构造方法

		public MyBehavior(Context context, AttributeSet attrs) {
		        super(context, attrs);
		}
在xml布局文件里给相应控件设置上layout_behavior属性

	    app:layout_behavior="luofeng.study.com.app1.MyBehavior"
3.第三个方法是在自定义控件时，利用注解框架绑定，参考FloatingActionButton  

* **AppBarLayout**  
AppBarLayout是对toolbar的在CoordinatorLayout上的外层嵌套，例如下滑隐藏，和悬停提示。如果是只要上滑就显示toolbar，把scrollFlags属性设置为enterAlways，如果要到滑动布局的最顶端再显示，则用enterAlwaysCollapsed。

		<android.support.design.widget.AppBarLayout
		        android:layout_width="match_parent"
		        android:layout_height="wrap_content"
		        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
		
		        <android.support.v7.widget.Toolbar
		            android:id="@+id/toolbar"
					app:layout_scrollFlags="scroll|enterAlwaysCollapsed">
		            android:layout_width="wrap_content"
		            android:layout_height="?actionBarSize">
		        </android.support.v7.widget.Toolbar>
		       
		        <TextView
		            android:layout_width="match_parent"
		            android:layout_height="wrap_content"
		            android:text="悬停条目"/>
		</android.support.design.widget.AppBarLayout>
这里需要注意的是，因为CoordinatorLayout是帧布局，在设置AppBarLayout后会遮住下面滚动布局的条目，可以通过修改它的layout_behavior属性避免。RecyclerView必须设置LayoutManager才可以使用。

		<android.support.v7.widget.RecyclerView
		        android:id="@+id/rv"
		        android:layout_width="match_parent"
		        android:layout_height="match_parent"
		        app:layoutManager="LinearLayoutManager"
		        app:layout_behavior="@string/appbar_scrolling_view_behavior">
		</android.support.v7.widget.RecyclerView>
* **CollapsingToolbarLayout**  
可折叠的toolbar，是appbarLayout+toolbar的一种升级版，使用方法是在上面的toolbar上套一层CollapsingToolbarLayout布局，并把scollFlags属性移给它，修改值为exitUntilCollapsed，在下滑隐藏时会保留一个actionbar的位置。

		<android.support.design.widget.CollapsingToolbarLayout
		            android:layout_width="match_parent"
		            android:layout_height="wrap_content"
		            app:contentScrim="@color/colorPrimary"
		            app:layout_scrollFlags="scroll|exitUntilCollapsed">

		            <ImageView
		                android:layout_width="match_parent"
		                android:layout_height="160dp"
		                app:layout_collapseMode="parallax"
		                android:src="@mipmap/ic_launcher"/>

		            <android.support.v7.widget.Toolbar
		                android:id="@+id/toolbar"  android:layout_width="wrap_content"
		                android:layout_height="?actionBarSize">
		            </android.support.v7.widget.Toolbar>

        </android.support.design.widget.CollapsingToolbarLayout>
设置的ImageView可以有一个压缩的过程，CollapsingToolbarLayout的app:contentScrim是对压缩后的toolbar的遮蔽处理，通常设置为toolbar原来的颜色，imageview的app:layout_collapseMode属性指定了折叠模式，默认是向上推进，parallax是上下同时压缩。

* **FloatingActionButton**  
浮动按钮，跟ImageButton最大的不同是，它和SnackBar处在同一层，它会因为SnackBar的show而升高，这也是它的名字的来由。它一般放置在AppBar的右下方。
		
		<android.support.design.widget.FloatingActionButton
		        android:id="@+id/fab"
		        app:layout_anchor="@id/appbar"
		        app:layout_anchorGravity="end|bottom"
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content"
		        android:src="@mipmap/ic_launcher"/>
FloatingActionButton的位置可以随意定，可以修改它的默认Behavior但是，重新设置后会覆盖原来的行为，因此，在使用时尽量去继承自具体的FloatingActionButton的Behavior类。

		<android.support.design.widget.FloatingActionButton
		        android:id="@+id/fab"
		        android:layout_gravity="end|bottom"
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content"
		        app:layout_behavior="luofeng.study.com.app1.MyBehavior"
		        android:src="@mipmap/ic_launcher"/>
###TextInputLayout
文本输入布局，对输入文本有所优化，例如text提示，和基本的输入要求判断提示。使用时对EditText做一层包装

	<android.support.design.widget.TextInputLayout
	        android:id="@+id/til"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content">
	        <EditText
	            android:layout_width="match_parent"
	            android:layout_height="wrap_content"
	            android:hint="用户名"/>
	</android.support.design.widget.TextInputLayout>
通过对EditText文本输入的监听，调用TextInputLayout相关方法提示

	textInput = (TextInputLayout) findViewById(R.id.til);
	textInput.getEditText().addTextChangedListener(this);
在输入文本时，会有一个错误提示
    
    public void afterTextChanged(Editable editable) {
        if (editable.length()<6) {
            textInput.setError("用户名不得少于6位");
            textInput.setErrorEnabled(true);
        }else {
            textInput.setErrorEnabled(false);
        }
    }