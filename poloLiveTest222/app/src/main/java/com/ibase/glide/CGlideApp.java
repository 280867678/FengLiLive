package com.ibase.glide;

//package com.ibase.glide;

import android.content.Context;
import android.os.Environment;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//import p067e.p260n.p261a.C4712a;
//import p067e.p260n.p261a.IPicture;
//import p067e.p260n.p261a.MyModelLoader;
//import p067e.p260n.p261a.p262i.ProgressManager;

@GlideModule
public class CGlideApp extends AppGlideModule {

    /* renamed from: com.ibase.glide.CGlideApp$a */
    /* loaded from: classes.dex */
    public class C1628a implements GlideExecutor.UncaughtThrowableStrategy {
        public C1628a(CGlideApp cGlideApp) {
        }

        @Override // com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
        public void handle(Throwable th) {
        }
    }

    /* renamed from: com.ibase.glide.CGlideApp$b */
    /* loaded from: classes.dex */
    public class C1629b implements FileLoader.FileOpener<InputStream> {
        public C1629b(CGlideApp cGlideApp) {
        }

        /* renamed from: a */
        public void close(InputStream inputStream) throws IOException {
            inputStream.close();
        }

        @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
        public InputStream open(File file) throws FileNotFoundException {
            String absolutePath;
            if (file == null) {
                return null;
            }
            if (C4712a.m4957a(file.getAbsolutePath())) {
                try {
                    absolutePath = C4712a.m4955b().m4958a(file);
                    if (absolutePath == null) {
                        absolutePath = file.getAbsolutePath();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    absolutePath = file.getAbsolutePath();
                }
            } else {
                absolutePath = file.getAbsolutePath();
            }
            if (absolutePath == null) {
                return null;
            }
            return new FileInputStream(new File(absolutePath));
        }
    }

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        C1628a aVar = new C1628a(this);
        long j = 524288000;
        glideBuilder.setMemoryCache(new LruResourceCache(j)).setBitmapPool(new LruBitmapPool(j)).setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(aVar)).setSourceExecutor(GlideExecutor.newSourceExecutor(aVar));
        if ("mounted".equals(Environment.getExternalStorageState())) {
            glideBuilder.setDiskCache(new DiskLruCacheFactory(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "boluovl/cache/img_cache", 629145600));
            return;
        }
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, "boluovl/cache/img_cache", 629145600));
    }

    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(ProgressManager.m4945a()));
        registry.append(IPicture.class, InputStream.class, new MyModelLoader.C4713a(context));
        registry.append(File.class, InputStream.class, new FileLoader.Factory(new C1629b(this)));
    }
}

