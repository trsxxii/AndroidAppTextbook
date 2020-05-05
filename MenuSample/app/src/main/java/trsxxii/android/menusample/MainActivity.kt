package trsxxii.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var _menuList: MutableList<MutableMap<String, Any>>? = null
    private val FROM = arrayOf("name", "price")
    private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
        registerForContextMenu(lvMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuListOptionTeishoku -> {
                _menuList = createTeishokuList()
            }
            R.id.menuListOptionCurry -> {
                _menuList = createCurryList()
            }
        }

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        lvMenu.adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderTitle(R.string.menu_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList!![listPosition]

        when (item.itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(applicationContext, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                order(menu)
            }
        }

        return super.onContextItemSelected(item)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menu = mutableMapOf<String, Any>("name" to "唐揚げ定食", "price" to 800, "desc" to "若鶏の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 850, "desc" to "生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1000, "desc" to "サーロインステーキにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 750, "desc" to "新鮮野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 900, "desc" to "とんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to 850, "desc" to "揚げたてミンチかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 850, "desc" to "揚げたてコロッケにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 800, "desc" to "焼き魚にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "お刺身定食", "price" to 900, "desc" to "新鮮なお刺身にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to 1050, "desc" to "ジューシーな焼肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        return menuList
    }

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産ビーフ100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "ポークカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産ポーク100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "チキンカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産チキン100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "シーフードカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産シーフード100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "キーマカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産キーマ100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "フルーツカレー", "price" to 520, "desc" to "特選スパイスを効かせた国産フルーツ100%のカレーです。")
        menuList.add(menu)
        return  menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }
    }

    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        val intent = Intent(applicationContext, MenuThanksActivity::class.java)
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", "${menuPrice}円")
        startActivity(intent)
    }
}
