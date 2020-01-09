package com.gym.chater.Emoji;

import android.content.Context;

import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;


public class CompatEmoji {
    static private Context context;

    static public void InitCompatEmoji(Context context) {
        EmojiCompat.Config config = new BundledEmojiCompatConfig(context);
        EmojiCompat.init(config);
    }
}
