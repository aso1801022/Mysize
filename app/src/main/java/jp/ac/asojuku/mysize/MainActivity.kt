package jp.ac.asojuku.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*pushの練習コメント*/
    /*コミットアンドプッシュの練習コメント*/
//PULL リモートno反映練習
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
//再表示のたびに呼ばれるライフサイクルイベントのコールバックメソッド
    override fun onResume() {
        super.onResume()
    //入力値を端末内に保存
    //共有プリファレンスのインスタンスを取得
    val pref=PreferenceManager.getDefaultSharedPreferences(this);
    //共有プリファレンスから４つの保存地を取り出す
    val editNeck=pref.getString("NECK","");
    val editSleeve=pref.getString("SLEEVE","");
    val editWaist=pref.getString("WAIST","");
    val editInseam=pref.getString("INSEAM","");
    //取得した保存地で各入力の表示を上書きする
    neck.setText(editNeck);//取得した首回りで更新
    sleeve.setText(editSleeve);
    waist.setText(editWaist);
    inseam.setText(editInseam);
    //保存ボタンが呼び出された時の処理
    save.setOnClickListener{onSaveTapped()}

    //身長アイコンボタンのクリック時の画面遷移を設定
    heightButton.setOnClickListener{
        //クリックした時に呼び出す処理
        //インテントを定義（どこからどこまで情報）
        val intent=Intent(this,HeightActivity::class.java);
        //画面遷移メソッドを実行
        this.startActivity(intent);

    }


    }
    private fun onSaveTapped() {
        //画面表示の値を共有プリファレンスで取得
        //共有プリファレンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        //共有プリファレンスを更新
        pref.edit {
            this.putString("NECK",neck.text.toString());//首回りの入力値を共有プリファレンスに保存する
            this.putString("SLEEVE",sleeve.text.toString());
            this.putString("WAIST",waist.text.toString());
            this.putString("INSEAM",inseam.text.toString());
        }
    }
}
