package com.example.badner.change.actionbar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import android.text.style.ForegroundColorSpan;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		ColorPickerDialog.OnColorChangedListener {

	public Paint mPaint;//לבחירת הצבע
	
	public int Flag_interchanges =0;//לטקסט יהיה 2 צבע רקע 1

	public String text_bar="HI from eyal";//מה יהיה כתוב בבר
	
	 public Typeface font; 
	
	 public int itxtcolor=0xffffffff;//צבע טקסט לאחר שינוי
	
	 
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//לצל של הכתב 
		TextView textv = (TextView) findViewById(R.id.textview2);
		textv.setShadowLayer(30, 0, 0, Color.RED);
		
		
	  //font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/CinzelDecorative-Regular.ttf");
	  //font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Maximum.ttf");
	  font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Orbitron-Bold.ttf");

	  
	  mPaint = new Paint();
		
		
		
		//צבע התחלה של הבר
		int icolor = R.color.Bisque;
		Drawable colorDrawable = new ColorDrawable(icolor);
		setActionBarBackgroundonecolor(colorDrawable);
		
		
		//שינוי איקון
		Button bicon = (Button) findViewById(R.id.buttonReplaceIcon);
		bicon.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
									
								
								MyDialogdialogicon idialogicon = new MyDialogdialogicon(MainActivity.this);
								
								idialogicon.show();
								
								
							}

						});
		
		
		
		
		
		//שינוי פונט
		Button bfond = (Button) findViewById(R.id.buttonFont);
		bfond.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						//font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/CinzelDecorative-Regular.ttf");
						//setActionBarTaxtecolorfond();	
						
						MyDialogdialogfont idialog = new MyDialogdialogfont(MainActivity.this);
						
						
						idialog.show();
						
						
					}

				});
		
		
		//כאשרלוחצים על שינוי צבע בר
		Button b = (Button) findViewById(R.id.buttonColorBar);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Flag_interchanges=1;
				new ColorPickerDialog(MainActivity.this, MainActivity.this,
						mPaint.getColor()).show();
			}

		});

		
		
		
		
		//שינוי צבע טקסט
		Button te =(Button) findViewById(R.id.buttonFontColor);
		te.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Flag_interchanges=2;
				new ColorPickerDialog(MainActivity.this, MainActivity.this,
						mPaint.getColor()).show();	
				
				
				
			}
		});
	}

	
	
	//צבע טקסט
	private void setActionBarTaxtecolorfond() {
		
		//itxtcolor =הצבע של הטקסט
		  SpannableStringBuilder sb = new SpannableStringBuilder(text_bar);
		   // final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(158, 158, 158)); 
		    ForegroundColorSpan fcs = new ForegroundColorSpan(itxtcolor); 

 
		   //לפונט
		   sb.setSpan (new CustomTypefaceSpan("", font), 0,  text_bar.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		   //לצבע
		   sb.setSpan(fcs, 0, text_bar.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); 
   
		   ActionBar actionBar = getActionBar();
		   actionBar.setTitle(sb);
		   
		 //  actionBar.setIcon(R.drawable.imagesicon);
		    
		
	}
	
	
	//צבע רקע בר
	private void setActionBarBackgroundonecolor(Drawable color) {
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(color);
	}


	
	//פונקציה לבר שני צבעים
		 @SuppressLint("NewApi")
		private void setActionBarBackgroundTwoColor(int upcolor,int downcolor) {
		        ActionBar actionbar = getActionBar();
		        //Drawable d = WidgetUtils.getActionBarDrawable(TestActivity.this, 0xFF00FFFF);

		        GradientDrawable gradientdrawable = new GradientDrawable(
		                GradientDrawable.Orientation.TOP_BOTTOM,
		                new int[]{upcolor, downcolor});
		        gradientdrawable.setCornerRadius(0f);
		        actionbar.setBackgroundDrawable(gradientdrawable);
		    }
	
	
	
	@Override
	public void colorChanged(int color) {
		
		//שינוי צבע הבר
		if (Flag_interchanges==1){
		ActionBar bar = MainActivity.this.getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(color));
		}
		//שינוי צבע הטקסט
		else if(Flag_interchanges==2){
			itxtcolor=color;
			setActionBarTaxtecolorfond();	
		}
	}
	/** קלאס דילוג פונוט */
	private class MyDialogdialogfont extends Dialog {

		public MyDialogdialogfont(Context context) {
			super(context);
			setContentView(R.layout.dialogfont);
			setTitle("Choose your font");

			final Typeface font1 = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/CinzelDecorative-Regular.ttf");
			final Typeface  font2 = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/SpecialElite.ttf");
			final Typeface  font3 = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Orbitron-Bold.ttf");
           
			TextView ifont1=(TextView)findViewById(R.id.textViewfont1);
			TextView ifont2=(TextView)findViewById(R.id.textViewfont2);
			TextView ifont3=(TextView)findViewById(R.id.textViewfont3);
			
			ifont1. setTypeface(font1);
			ifont2. setTypeface(font2);
			ifont3. setTypeface(font3);
			
			
			ifont1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Toast.makeText(MainActivity.this,
							"Change font successfully", Toast.LENGTH_LONG)
							.show();
					font=font1;
					setActionBarTaxtecolorfond();
					dismiss();
					

				}
			});

			
			ifont2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Toast.makeText(MainActivity.this,
							"Change font successfully",
							Toast.LENGTH_LONG).show();
					
					font=font2;
					setActionBarTaxtecolorfond();
					dismiss();
				

				}
			});
		
		ifont3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Toast.makeText(MainActivity.this,
						"Change font successfully",
						Toast.LENGTH_LONG).show();
				
				font=font3;
				setActionBarTaxtecolorfond();
				dismiss();
			

			}
		});
	}
	}
	
	/** קלאס דילוג איקון */
	private class MyDialogdialogicon extends Dialog {

		public MyDialogdialogicon(Context context) {
			super(context);
			setContentView(R.layout.dialogicon);
			setTitle("Choose your icon");

			
           
			ImageView icon1=(ImageView)findViewById(R.id.imageView_iconandroid);
			ImageView icon2=(ImageView)findViewById(R.id.imageView_imagesicon);
			ImageView icon3=(ImageView)findViewById(R.id.imageView_ic_launcher);
			
			 final ActionBar actionBar = MainActivity.this.getActionBar();
			
			   
			 
			
			
			icon1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Toast.makeText(MainActivity.this,
							"Change icon successfully", Toast.LENGTH_LONG)
							.show();
				
					 actionBar.setIcon(R.drawable.iconandroid);
					dismiss();
					

				}
			});

			
			icon2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Toast.makeText(MainActivity.this,
							"Change icon successfully",
							Toast.LENGTH_LONG).show();
					
					
					 actionBar.setIcon(R.drawable.imagesicon);
					dismiss();
				

				}
			});
		
			icon3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Toast.makeText(MainActivity.this,
						"Change icon successfully",
						Toast.LENGTH_LONG).show();
				
				
				 actionBar.setIcon(R.drawable.ic_launcher);
				dismiss();
			

			}
		});
	}
	}
	
}
