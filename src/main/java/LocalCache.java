/*
 * Copyright (C) 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author James Moger
 */
public class LocalCache extends Capsule {

    static {
        setLocalCache();
    }

    public static void setLocalCache() {
        try {
            Path capsuleJarPath = Paths.get(Capsule.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File localCapsuleDir = new File(capsuleJarPath.toFile().getParentFile(), ".capsule");
            String cacheDir = Optional.ofNullable(System.getProperty("cache.dir")).orElse(localCapsuleDir.getAbsolutePath());

            Path cachePath = new File(cacheDir).toPath();
            Field field = Capsule.class.getDeclaredField("CACHE_DIR");
            field.setAccessible(true);
            field.set(null, cachePath);
            Path readPath = (Path) field.get(null);
            if (readPath.equals(cachePath)) {
                log(LOG_VERBOSE, LocalCache.class.getName() + " set " + field.getName() + "=" + readPath);
            } else {
                log(LOG_VERBOSE, LocalCache.class.getName() + " FAILED TO SET " + field.getName() + "=" + readPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LocalCache(Capsule pred) {
        super(pred);
    }
}
