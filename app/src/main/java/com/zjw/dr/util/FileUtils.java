package com.zjw.dr.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    /**
     * 判断外部存储是否挂载
     */
    public static boolean isExternalMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取外部存储根目录的绝对路径
     */
    public static String getExternalBaseDir() {
        if (isExternalMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 保存文件到外部存储的公有目录
     */
    public static boolean saveFileToExnternalPublicDir(byte[] data, String type, String fileName) {
        if (isExternalMounted()) {
            BufferedOutputStream bos = null;
            File file = Environment.getExternalStoragePublicDirectory(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 保存文件到外部存储的自定义文件夹
     */
    public static File saveToExternalCustomDir(byte[] data, String dir, String fileName) {
        if (isExternalMounted()) {
            BufferedOutputStream bos = null;
            File file = new File(getExternalBaseDir() + File.separator + dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                File saveFile = new File(file, fileName);
                bos = new BufferedOutputStream(new FileOutputStream(saveFile));
                bos.write(data);
                bos.flush();
                return saveFile;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 保存文件到外部存的私有Files目录
     */
    public static boolean saveFileToExternalPrivateFilesDir(byte[] data, String type, String fileName,
                                                            Context context) {
        if (isExternalMounted()) {
            BufferedOutputStream bos = null;
            File file = context.getExternalFilesDir(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 保存文件到外部存储的私有Cache目录
     */
    public static boolean saveFileToExternalPrivateCacheDir(byte[] data, String fileName, Context context) {
        if (isExternalMounted()) {
            BufferedOutputStream bos = null;
            File file = context.getExternalCacheDir();
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 保存Bitmap到外部存储的私有Cache目录
     */
    public static boolean saveBitmapToExternalPrivateCacheDir(Bitmap bitmap, String fileName, Context context) {
        if (isExternalMounted()) {
            BufferedOutputStream bos = null;
            File file = context.getExternalCacheDir();
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                if (fileName != null && (fileName.contains(".png") || fileName.contains(".PNG"))) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                }
                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 从外部存储获取文件
     */
    public static byte[] loadFileFromExternal(String fileDir) {
        if (isExternalMounted()) {
            BufferedInputStream bis = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                bis = new BufferedInputStream(new FileInputStream(new File(fileDir)));
                byte[] buffer = new byte[8 * 1024];
                int c;
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }
                return baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] getBytesFromFile(File file) {
        if (isExternalMounted()) {
            if (file == null) return null;
            try {
                FileInputStream stream = new FileInputStream(file);
                ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];
                int n;
                while ((n = stream.read(b)) != -1) out.write(b, 0, n);
                stream.close();
                out.close();
                return out.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 从外部存储中获取指定的Bitmap
     */

    public Bitmap loadBitmapFromExternal(String filePath) {
        byte[] data = loadFileFromExternal(filePath);
        if (data != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bm != null) {
                return bm;
            }
        }
        return null;
    }

    /**
     * 获取外部存储公有目录的绝对路径
     */
    public static String getExternalPublicDir(String type) {
        return Environment.getExternalStoragePublicDirectory(type).toString();
    }

    /**
     * 获取外部存储私有Cache目录的绝对路径
     */
    public static String getExternalPrivateCacheDir(Context context) {
        return context.getExternalCacheDir().getAbsolutePath();
    }

    /**
     * 获取外部存储私有Files目录的绝对路径
     */
    public static String getExternalPrivateFilesDir(Context context, String type) {
        return context.getExternalFilesDir(type).getAbsolutePath();
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除某个文件夹下的文件
     */
    public static boolean deleteFiles(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            return true;
        }

        return false;
    }

    /**
     * 删除内部存储中的缓存
     */
    public static boolean deleteInternalCache(Context context) {
        return deleteFiles(context.getCacheDir().getAbsolutePath());
    }

    /**
     * 删除外部存储中的缓存
     */
    public static boolean deleteExternalCache(Context context) {
        return deleteFiles(context.getExternalCacheDir().getAbsolutePath());
    }
}