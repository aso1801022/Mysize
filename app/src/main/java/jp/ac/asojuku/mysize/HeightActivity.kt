package jp.ac.asojuku.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
    }
//再表示の時に呼ばれるライフサイクルのコールバックメゾット
    override fun onResume() {
        super.onResume()
    //スピナーにitem（選択肢）が選ばれた時のコールバックメソッドを設定
    spinner.onItemSelectedListener= //スピナーにアイテムを選んだ時の動きを持ったクラスの匿名インスタンスを代入
        object:AdapterView.OnItemSelectedListener{//アイテムを選んだ時の動きを持ったクラスの継承クラスを定義し匿名インスタンスにする
            override fun onItemSelected(//アイテムを選んだ時の処理
                parent: AdapterView<*>?,//洗濯が発生したビュー（スピナー）
                p1: View?,//洗濯されたビュー（アイテム）
                p2: Int, //選択肢が何番目か
                p3: Long//洗濯されたアイテムのID
            ) {
               //選択肢を取得するためにスピナーのインスタンスを取得する
            val spinner = parent as? Spinner;
            //選択肢を取得
            val item =spinner?.selectedItem as? String;
            //取得した値を身長のテキストビューに上書き
            item?.let{
                if(it.isNotEmpty()){height.text=it}//it 身長の値が空文字でなければ、身長のテキストビューに代入
            }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {//アイテムを何も選ばなかった時の処理
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //何もしない
            }
        }
    //シークバーの処理を定義する
    //共有プリファレンスから身長設定値を取得する（シークバーの表示のため）
    val pref=PreferenceManager.getDefaultSharedPreferences(this);
    val heightval=pref.getInt("HEIGHT",160);//身長の値を保存
    height.text=heightval.toString();//身長の値も上書き
    //シークバーの現在値
    seekBar.progress = heightval;
    seekBar.setOnSeekBarChangeListener(
        object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar,//値が変化したシークバーのインスタンス
                progress:Int,//私が変化したシークバーの現在地
                fromUser: Boolean//ユーザーが操作したか
            ) {
                //ユーザーの指定地で「身長」の表示を変える
               height.text= progress.toString();


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //なにもしない
            }
            //3つm
            override fun onStopTrackingTouch(p0: SeekBar?) {
                //なにもしない

            }
        }
    )
    }
    //画面が閉じられる時に呼ばれるライフサイクルコールメゾッっと
    override fun onPause() {
        super.onPause()
        //身長の現在地を共有プリファレンスに保存する処理を実装
        //共有プリファレンスのインスタンスを取得
        val pref=PreferenceManager.getDefaultSharedPreferences(this);
        pref.edit{
            this.putInt("HEIGHT",height.text.toString().toInt()) ;}
    }
}
